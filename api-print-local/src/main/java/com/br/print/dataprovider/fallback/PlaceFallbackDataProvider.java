package com.br.print.dataprovider.fallback;

import com.br.print.core.usecase.http.FallbackHttModel;
import com.br.print.dataprovider.feign.PlaceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PlaceFallbackDataProvider implements PlaceFeign {

    private final String messageUnavailable = "Dados indisponíveis momentaneamente!";
    private final String methodAccessedListStates = "listStates()";
    private final String methodAccessedListCitiesByState = "listCitiesByState()";

   @Override
    public List<Object> listStates() {
       return Arrays.asList(new FallbackHttModel().builder()
                                                  .dateTime(LocalDateTime.now())
                                                  .method(methodAccessedListStates)
                                                  .message(messageUnavailable)
                                                  .build());
   }

    @Override
    public List<Object> listCitiesByState(@PathVariable("uf") String uf) {
        return Arrays.asList(new FallbackHttModel().builder()
                                                    .dateTime(LocalDateTime.now())
                                                    .method(methodAccessedListCitiesByState)
                                                    .message(messageUnavailable)
                                                    .build());
    }

}
