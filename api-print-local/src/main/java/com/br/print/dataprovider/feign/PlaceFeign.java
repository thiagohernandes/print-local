package com.br.print.dataprovider.feign;

import com.br.print.core.usecase.http.CityModelHttp;
import com.br.print.core.usecase.http.StateModelHttp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${feign.ibge.name}", url = "${feign.ibge.url}")
public interface PlaceFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/localidades/estados")
    public List<StateModelHttp> listStates();

    @RequestMapping(method = RequestMethod.GET, value = "/localidades/estados/{UF}/municipios")
    public List<CityModelHttp> listCitiesByState(@PathVariable("UF") String uf);

}
