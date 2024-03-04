package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ListAccountsOutputDto(
       @JsonProperty("accountId") String accountId,
       @JsonProperty("clientId") String clientId,
       @JsonProperty("agencyNumber") String agencyNumber,
       @JsonProperty("accountNumber") String accountNumber,
       @JsonProperty("balance") int balance,
       @JsonProperty("limit") int limit,
       @JsonProperty("accountType") String accountType,
       @JsonProperty("createdAt") Instant createdAt
) {
}
