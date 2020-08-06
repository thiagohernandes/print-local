package com.br.print.dataprovider.gateway;

import java.util.List;

public interface PlaceGateway {

    List<Object> listStates();

    List<Object> listCitiesByState(String uf);

}
