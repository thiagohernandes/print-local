package com.br.print.builder.data;

import com.br.print.core.usecase.http.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceBuildDataTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static final List<Object> makeMockListObjectStates() {
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

    public static final List<Object> makeMockListCities() {
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
