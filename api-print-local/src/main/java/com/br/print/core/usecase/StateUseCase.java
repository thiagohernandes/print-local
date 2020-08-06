package com.br.print.core.usecase;

import com.br.print.core.usecase.http.CityModelHttp;
import com.br.print.core.usecase.http.StateModelHttp;
import com.br.print.dataprovider.PlaceDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateUseCase {

    private final PlaceDataProvider placeDataProvider;

    @Autowired
    public StateUseCase(final PlaceDataProvider placeDataProvider) {
        this.placeDataProvider = placeDataProvider;
    }

    public List<StateModelHttp> listStates() {
        return this.placeDataProvider.listStates();
    }

    public List<CityModelHttp> listCitiesByState(String uf) {
        return this.placeDataProvider.listCitiesByState(uf);
    }
}
