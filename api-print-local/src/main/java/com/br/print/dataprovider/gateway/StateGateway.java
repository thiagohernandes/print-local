package com.br.print.dataprovider.gateway;

import com.br.print.core.usecase.http.StateModelHttp;

import java.util.List;

public interface StateGateway {

    List<StateModelHttp> listStates();

}
