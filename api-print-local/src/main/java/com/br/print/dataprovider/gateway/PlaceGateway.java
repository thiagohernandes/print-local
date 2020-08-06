package com.br.print.dataprovider.gateway;

import com.br.print.core.usecase.http.CityModelHttp;
import com.br.print.core.usecase.http.StateModelHttp;

import java.util.List;

public interface PlaceGateway {

    List<StateModelHttp> listStates();

    List<CityModelHttp> listCitiesByState(String uf);

}
