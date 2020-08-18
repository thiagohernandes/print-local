package com.br.print.dataprovider;

import com.br.print.core.usecase.http.CityHttpModel;
import com.br.print.dataprovider.feign.PlaceFeign;
import com.br.print.dataprovider.gateway.PlaceGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class PlaceDataProvider implements PlaceGateway {

    private final PlaceFeign placeFeign;
    private final CacheManager cacheManager;

    @Autowired
    public PlaceDataProvider(final PlaceFeign placeFeign, final CacheManager cacheManager) {
        this.placeFeign = placeFeign;
        this.cacheManager = cacheManager;
    }

    public List<Object> listStates(){
        return this.placeFeign.listStates();
    }

    @Cacheable(value = "stateCities", key = "#uf")
    public List<Object> listCitiesByState(String uf){
        return this.placeFeign.listCitiesByState(uf);
    }

    public byte[] citiesPdfReport(String uf) throws IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<Object> listCities = this.listCitiesByState(uf);
        ObjectMapper mapper = new ObjectMapper();
        List<CityHttpModel> listMapperList = mapper.convertValue(listCities, new TypeReference<List<CityHttpModel>>() { });

        try {
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{20, 14, 25, 60, 30, 60});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id UF", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Sigla UF", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Região", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cidade", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Mesorregião", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cidade/Estado", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (CityHttpModel cityHttpModel : listMapperList) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(cityHttpModel.getId().toString(), headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(cityHttpModel.getMicroRegiao().getMesoRegion()
                                                             .getUf().getSigla(), headFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(cityHttpModel.getMicroRegiao()
                                                .getMesoRegion().getUf().getRegiao().getNome()), headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(cityHttpModel.getNome()), headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(cityHttpModel.getMicroRegiao()
                                                    .getMesoRegion().getNome()), headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(cityHttpModel.getNome().concat("/")
                                        .concat(cityHttpModel.getMicroRegiao().getMesoRegion()
                                        .getUf().getSigla()), headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

        } catch (DocumentException ex) {
            log.error("Error occurred on create PDF: {}", ex);
        } finally {
            document.close();
        }
        return out.toByteArray();
    }
}
