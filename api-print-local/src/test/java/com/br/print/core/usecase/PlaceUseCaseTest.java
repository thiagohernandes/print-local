package com.br.print.core.usecase;

import com.br.print.builder.data.PlaceBuildDataTest;
import com.br.print.dataprovider.PlaceDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class PlaceUseCaseTest {

    @InjectMocks
    private PlaceUseCase placeUseCase;

    @Mock
    private PlaceDataProvider placeDataProvider;

    @Test
    public void listStates__Success_Test() {
        Mockito.when(this.placeDataProvider.listStates())
                .thenReturn(PlaceBuildDataTest.makeMockListObjectStates());
        this.placeUseCase.listStates();
    }

    @Test
    public void listCitiesByState__Success_Test() {
        Mockito.when(this.placeDataProvider.listCitiesByState("SP"))
                .thenReturn(PlaceBuildDataTest.makeMockListCities());
        this.placeUseCase.listCitiesByState("SP");
    }

    @Test
    public void listCitiesByStatePDF__Success_Test() throws IOException {
        Mockito.when(this.placeDataProvider.citiesPdfReport("SP"))
                .thenReturn(new String("teste").getBytes());
        this.placeUseCase.citiesPdfReport("SP");
    }

}
