package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;

public record GetClientOutputDto(
       @JsonProperty("clientId") String clientId,
       @JsonProperty("name") String name,
       @JsonProperty("email") String email,
       @JsonProperty("cpf") String cpf,
       @JsonProperty("birthDate") LocalDate birthDate,
       @JsonProperty("createdAt") Instant createdAt,
       @JsonProperty("updatedAt") Instant updatedAt
) {
}
