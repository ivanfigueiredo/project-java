package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionDepositInput(
        @JsonProperty("accountId") String accountId,
        @JsonProperty("amount") int amount
) {
}
