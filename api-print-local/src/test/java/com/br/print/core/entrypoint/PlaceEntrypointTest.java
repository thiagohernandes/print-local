package com.br.print.core.entrypoint;

import com.br.print.builder.data.PlaceBuildDataTest;
import com.br.print.core.usecase.PlaceUseCase;
import com.br.print.core.usecase.http.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PlaceEntrypointTest {

    @Mock
    private PlaceUseCase stateUseCase;

    @InjectMocks
    private PlaceEntrypoint placeEntrypoint;

    @Autowired
    private PlaceBuildDataTest placeBuildDataTest;

    @Test
    public void listStates__Success__Test() throws Exception {
        Mockito.when(this.stateUseCase.listStates())
                .thenReturn(this.placeBuildDataTest.makeMockListObjectStates());
        ResponseEntity<List<Object>> listStates = this.placeEntrypoint.listStates();
        assertNotNull(listStates);
        assertTrue(listStates.getBody().size() > 0);
    }

    @Test
    public void listCitiesByStates__Success__Test() throws Exception {
        Mockito.when(this.stateUseCase.listCitiesByState("MG"))
                .thenReturn(this.placeBuildDataTest.makeMockListCities());
        ResponseEntity<List<Object>> listCities = this.placeEntrypoint.listCitiesByState("MG");
        assertNotNull(listCities);
        assertTrue(listCities.getBody().size() > 0);
    }

    @Test
    public void listCitiesByStatePdfReport__Success__Test() throws Exception {
        Mockito.when(this.stateUseCase.citiesPdfReport("MG"))
                .thenReturn(Mockito.mock(ByteArrayInputStream.class));
        ResponseEntity<InputStreamResource> listCitiesPdf = this.placeEntrypoint
                .listCitiesByStatePdfReport("MG");
        assertNotNull(listCitiesPdf);
    }

}
