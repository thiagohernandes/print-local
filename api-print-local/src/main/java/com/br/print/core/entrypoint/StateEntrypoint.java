package com.br.print.core.entrypoint;

import com.br.print.core.usecase.StateUseCase;
import com.br.print.core.usecase.http.StateModelHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/states")
public class StateEntrypoint {

    private final StateUseCase stateUseCase;

    @Autowired
    public StateEntrypoint(final StateUseCase stateUseCase) {
        this.stateUseCase = stateUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<StateModelHttp>> listStates(){
       return ResponseEntity.status(HttpStatus.OK)
                            .body(this.stateUseCase.listStates());
    }
}
