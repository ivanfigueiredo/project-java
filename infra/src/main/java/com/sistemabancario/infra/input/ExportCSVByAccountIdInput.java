package com.sistemabancario.infra.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExportCSVByAccountIdInput(
        @JsonProperty("accountId") String accountId
) {}
