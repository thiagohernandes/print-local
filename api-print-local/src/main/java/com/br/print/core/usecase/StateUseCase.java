package com.br.print.core.usecase;

import com.br.print.dataprovider.PlaceDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class StateUseCase {

    private final PlaceDataProvider placeDataProvider;

    @Autowired
    public StateUseCase(final PlaceDataProvider placeDataProvider) {
        this.placeDataProvider = placeDataProvider;
    }

    public List<Object> listStates() {
        return this.placeDataProvider.listStates();
    }

    public List<Object> listCitiesByState(String uf) {
        return this.placeDataProvider.listCitiesByState(uf);
    }

    public ByteArrayInputStream citiesPdfReport(String uf) throws IOException {
        return this.placeDataProvider.citiesPdfReport(uf);
    }
}
