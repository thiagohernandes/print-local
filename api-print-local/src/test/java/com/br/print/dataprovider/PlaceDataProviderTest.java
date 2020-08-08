package com.br.print.dataprovider;

import com.br.print.core.usecase.http.*;
import com.br.print.dataprovider.feign.PlaceFeign;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PlaceDataProviderTest {

    @InjectMocks
    private PlaceDataProvider placeDataProvider;

    @Mock
    private PlaceFeign placeFeign;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listStates__Success__Test() {
        Mockito.when(this.placeFeign.listStates())
                .thenReturn(makeMockListObjectStates());
        List<Object> listStates = this.placeDataProvider.listStates();
        assertNotNull(listStates);
        assertTrue(listStates.size() > 0);
    }

    @Test
    public void listCities__By__State__Success__Test(){
        Mockito.when(this.placeFeign.listCitiesByState("MG"))
                .thenReturn(makeMockListCities());
        List<Object> listCities = this.placeDataProvider.listCitiesByState("MG");
        assertNotNull(listCities);
        assertTrue(listCities.size() > 0);
    }

    @Test
    public void citiesPDF__Report__Success__Test() throws IOException {
        this.placeDataProvider.citiesPdfReport("MG");
    }

    private final List<Object> makeMockListObjectStates() {
        StateRegionHttpModel regiao = StateRegionHttpModel.builder()
                                                          .id(3L)
                                                          .sigla("SE")
                                                          .nome("Sudeste")
                                                          .build();
        StateHttpModel stateHttpModelMG = StateHttpModel.builder()
                                                      .id(31L)
                                                      .sigla("MG")
                                                      .nome("Minas Gerais")
                                                      .regiao(regiao)
                                                      .build();
        StateHttpModel stateHttpModelES = StateHttpModel.builder()
                .id(32L)
                .sigla("ES")
                .nome("Espírito Santo")
                .regiao(regiao)
                .build();
        List<StateHttpModel> listStateHttpModel = new ArrayList<>();
        listStateHttpModel.add(stateHttpModelES);
        listStateHttpModel.add(stateHttpModelMG);

        return mapper.convertValue(listStateHttpModel, new TypeReference<List<Object>>() { });
    }

    private final List<Object> makeMockListCities() {
        StateRegionHttpModel stateRegionHttpModel = StateRegionHttpModel.builder()
                .id(3L)
                .nome("Sudeste")
                .sigla("SE")
                .build();
        StateHttpModel stateHttpModel = StateHttpModel.builder()
                  .id(31L)
                  .sigla("MG")
                  .nome("Minas Gerais")
                  .regiao(stateRegionHttpModel)
                  .build();
        StateMesoRegionHttpModel stateMesoRegionHttpModel = StateMesoRegionHttpModel.builder()
                    .id(3105L)
                    .nome("Triângulo Mineiro/Alto Paranaíba")
                    .uf(stateHttpModel)
                    .build();
        StateMicroRegionHttpModel microRegionHttpModel = StateMicroRegionHttpModel.builder()
                    .id(31019L)
                    .nome("Patrocínio")
                    .mesoRegion(stateMesoRegionHttpModel)
                    .build();
        CityHttpModel cityHttpModel = CityHttpModel.builder()
                    .id(3100104L)
                    .nome("Abadia dos Dourados")
                    .microRegiao(microRegionHttpModel)
                    .build();
        List<CityHttpModel> listCityHttpModel = new ArrayList<>();
        listCityHttpModel.add(cityHttpModel);

        return mapper.convertValue(listCityHttpModel, new TypeReference<List<Object>>() { });
    }
}
