package com.sistemabancario.application.dto;

public class UpdateAccountLimitOutputDto {
    public final String accountId;
    public final int limit;

    public UpdateAccountLimitOutputDto(final String accountId, final int limit) {
        this.accountId = accountId;
        this.limit = limit;
    }
}
