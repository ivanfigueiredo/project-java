package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistemabancario.domain.AccountType;

public record CreateAccountInput(
        @JsonProperty("clientId") String clientId,
        @JsonProperty("agencyNumber") String agencyNumber,
        @JsonProperty("limit") int limit,
        @JsonProperty("balance") int balance,
        @JsonProperty("accountType") int accountType
) {
}
