package com.br.print.dataprovider;

import com.br.print.core.usecase.http.StateModelHttp;
import com.br.print.dataprovider.feign.StateFeign;
import com.br.print.dataprovider.gateway.StateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateDataProvider implements StateGateway {

    private final StateFeign stateFeign;

    @Autowired
    public StateDataProvider(final StateFeign stateFeign) {
        this.stateFeign = stateFeign;
    }

    public List<StateModelHttp> listStates(){
        return this.stateFeign.listStates();
    }

}
