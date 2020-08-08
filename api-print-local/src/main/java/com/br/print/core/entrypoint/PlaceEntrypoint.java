package com.br.print.core.entrypoint;

import com.br.print.core.usecase.PlaceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/v1/places")
public class PlaceEntrypoint {

    private final PlaceUseCase stateUseCase;

    @Autowired
    public PlaceEntrypoint(final PlaceUseCase stateUseCase) {
        this.stateUseCase = stateUseCase;
    }

    @GetMapping("/states")
    public ResponseEntity<List<Object>> listStates() throws Exception {
       return ResponseEntity.status(HttpStatus.OK)
                            .body(this.stateUseCase.listStates());
    }

    @GetMapping("/states/{uf}/cities")
    public ResponseEntity<List<Object>> listCitiesByState(@PathVariable("uf") String uf){
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.stateUseCase.listCitiesByState(uf));
    }

    @GetMapping(value = "/states/{uf}/cities/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> listCitiesByStatePdfReport(@PathVariable("uf") String uf) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reportPDFCidades.pdf");
        return ResponseEntity.status(HttpStatus.OK)
                            .headers(headers)
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(new InputStreamResource(this.stateUseCase.citiesPdfReport(uf)));
    }

}
