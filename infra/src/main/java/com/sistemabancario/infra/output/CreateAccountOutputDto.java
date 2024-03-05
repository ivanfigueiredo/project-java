package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAccountOutputDto(
        @JsonProperty("accountId") String accountId
) {
}
