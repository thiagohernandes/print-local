package com.br.print.core.entrypoint;

import com.br.print.core.handler.exception.HandlerExceptionNotFound;
import com.br.print.core.handler.exception.HandlerValidationException;
import com.br.print.core.usecase.StateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/v1/places")
public class PlaceEntrypoint {

    private final StateUseCase stateUseCase;

    @Autowired
    public PlaceEntrypoint(final StateUseCase stateUseCase) {
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

}
