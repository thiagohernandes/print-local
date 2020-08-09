package com.br.print.dataprovider.fallback;

import com.br.print.core.usecase.http.FallbackHttModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaceFallbackDataProviderTest {

    private final String messageUnavailable = "Dados indisponíveis momentaneamente!";
    private final String methodAccessedListStates = "listStates()";
    private final String methodAccessedListCitiesByState = "listCitiesByState()";

    @InjectMocks
    private PlaceFallbackDataProvider placeFallbackDataProvider;

    @Test
    public void listStates__Fallback__Test() {
        this.placeFallbackDataProvider.listStates();
    }

    @Test
    public void listCitiesByState__Fallback__Test() {
        this.placeFallbackDataProvider.listCitiesByState("MG");
    }

    private final List<Object> makeMockListStates() {
        return Arrays.asList(new FallbackHttModel().builder()
                .dateTime(LocalDateTime.now())
                .method(methodAccessedListStates)
                .message(messageUnavailable)
                .build());
    }

    private final List<Object> listCitiesByState() {
        return Arrays.asList(new FallbackHttModel().builder()
                .dateTime(LocalDateTime.now())
                .method(methodAccessedListCitiesByState)
                .message(messageUnavailable)
                .build());
    }

}
