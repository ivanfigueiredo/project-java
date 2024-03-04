package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Optional;

public record TransactionListOutputDto(
        @JsonProperty("transactionId") String transactionId,
        @JsonProperty("owner") String owner,
        @JsonProperty("accountId") String accountId,
        @JsonProperty("clientId") String clientId,
        @JsonProperty("balance") int balance,
        @JsonProperty("amount") int amount,
        @JsonProperty("transactionType") String transactionType,
        @JsonProperty("createdAt") Instant createdAt,
        @JsonProperty("accountIdTarget") Optional<String> accountIdTarget) {
}
