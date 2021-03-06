package com.br.print.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityHttpModel {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("microrregiao")
    private StateMicroRegionHttpModel microRegiao;

}

