package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAccountLimitInput(
        @JsonProperty("amount") int amount
) {
}
