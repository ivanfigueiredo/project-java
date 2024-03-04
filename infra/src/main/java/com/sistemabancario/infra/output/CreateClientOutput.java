package com.sistemabancario.infra.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateClientOutput(@JsonProperty("clientId") String clientId) {}
