package com.br.print.dataprovider;

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

    public List<Object> listStates(){
        return this.placeFeign.listStates();
    }

    public List<Object> listCitiesByState(String uf){
        return this.placeFeign.listCitiesByState(uf);
    }

}
