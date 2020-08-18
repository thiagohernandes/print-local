package com.br.print.core.entrypoint;

import com.br.print.core.handler.exception.HandlerValidationException;
import com.br.print.core.usecase.PlaceUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/v1/places")
@Api(value = "Places")
public class PlaceEntrypoint {

    private final PlaceUseCase stateUseCase;

    @Autowired
    public PlaceEntrypoint(final PlaceUseCase stateUseCase) {
        this.stateUseCase = stateUseCase;
    }

    @ApiOperation(value = "Retorna a lista de estados brasileiros")
    @GetMapping("/states")
    public ResponseEntity<List<Object>> listStates() throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.stateUseCase.listStates());
    }

    @ApiOperation(value = "Retorna a lista de cidades por unidade federativa brasileira")
    @GetMapping("/states/{uf}/cities")
    public ResponseEntity<List<Object>> listCitiesByState(@PathVariable("uf") String uf) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.stateUseCase.listCitiesByState(uf));
    }

    @ApiOperation(value = "Retorna o PDF com a lista de cidades por unidade federativa brasileira")
    @GetMapping(value = "/states/{uf}/cities/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]>  listCitiesByStatePdfReport(@PathVariable("uf") String uf)
                                                                         throws IOException {
        byte[] dataBytes = this.stateUseCase.citiesPdfReport(uf);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=citiesPDF.pdf" )
                .contentType(MediaType.APPLICATION_PDF) //
                .contentLength(dataBytes.length) //
                .body(dataBytes);
    }

}
