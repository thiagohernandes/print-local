package com.br.print.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FallbackHttModel {

    @JsonProperty("message")
    private String message;
    @JsonProperty("method")
    private String method;
    @JsonProperty("datetime")
    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime dateTime;

}
