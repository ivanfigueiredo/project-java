package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.wildfly.common.annotation.NotNull;

public record TransactionTransferInput(
        @JsonProperty("accountIdFrom")
        String accountIdFrom,
        @JsonProperty("accountIdTo")
        String accountIdTo,
        @JsonProperty("amount")
        int amount
) {
}
