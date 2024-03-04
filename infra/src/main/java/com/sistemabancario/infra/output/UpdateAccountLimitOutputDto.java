package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAccountLimitOutputDto(
        @JsonProperty("accountId") String accountId,
        @JsonProperty("limit") int limit
) {
}
