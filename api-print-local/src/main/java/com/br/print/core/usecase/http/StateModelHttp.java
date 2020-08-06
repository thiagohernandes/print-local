package com.br.print.core.usecase.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateModelHttp {

    private Long id;
    private String sigla;
    private String nome;
    private StateRegionModelHttp regiao;

}
