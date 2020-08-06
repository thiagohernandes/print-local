package com.br.print.dataprovider;

import com.br.print.core.usecase.http.CityModelHttp;
import com.br.print.core.usecase.http.StateModelHttp;
import com.br.print.dataprovider.feign.PlaceFeign;
import com.br.print.dataprovider.gateway.PlaceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceDataProvider implements PlaceGateway {

    private final PlaceFeign placeFeign;

    @Autowired
    public PlaceDataProvider(final PlaceFeign placeFeign) {
        this.placeFeign = placeFeign;
    }

    public List<StateModelHttp> listStates(){
        return this.placeFeign.listStates();
    }

    public List<CityModelHttp> listCitiesByState(String uf){
        return this.placeFeign.listCitiesByState(uf);
    }

}
