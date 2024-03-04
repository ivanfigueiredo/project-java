package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionWithdrawInput(
        @JsonProperty("accountId") String accountId,
        @JsonProperty("amount") int amount
) {
}
