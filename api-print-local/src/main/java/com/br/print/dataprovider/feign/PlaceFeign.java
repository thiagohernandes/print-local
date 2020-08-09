package com.br.print.dataprovider.feign;

import com.br.print.dataprovider.fallback.PlaceFallbackDataProvider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${feign.ibge.name}", url = "${feign.ibge.url}", fallback = PlaceFallbackDataProvider.class)
public interface PlaceFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/localidades/estados")
    List<Object> listStates();

    @RequestMapping(method = RequestMethod.GET, value = "/localidades/estados/{uf}/municipios")
    List<Object> listCitiesByState(@PathVariable("uf") String uf);

}
