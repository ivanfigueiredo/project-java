package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateClientInput(
        @JsonProperty("name") String name,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("email") String email,
        @JsonProperty("birthDate") String birthDate
) {
}
