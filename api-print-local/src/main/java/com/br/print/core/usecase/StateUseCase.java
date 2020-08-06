package com.br.print.core.usecase;

import com.br.print.core.usecase.http.StateModelHttp;
import com.br.print.dataprovider.StateDataProvider;
import com.br.print.dataprovider.gateway.StateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateUseCase {

    private final StateDataProvider stateDataProvider;

    @Autowired
    public StateUseCase(final StateDataProvider stateDataProvider) {
        this.stateDataProvider = stateDataProvider;
    }

    public List<StateModelHttp> listStates() {
        return this.stateDataProvider.listStates();
    }
}
