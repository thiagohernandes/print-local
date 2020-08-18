package com.br.print.dataprovider.gateway;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface PlaceGateway {

    List<Object> listStates();

    List<Object> listCitiesByState(String uf);

    byte[] citiesPdfReport(String uf) throws IOException;

}
