package com.sistemabancario.application.dto;

public class UpdateAccountLimitDto {
    public final String accountId;
    public final int limit;

    public UpdateAccountLimitDto(final String accountId, final int limit) {
        this.accountId = accountId;
        this.limit = limit;
    }
}
