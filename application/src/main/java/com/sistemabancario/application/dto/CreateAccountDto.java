package com.sistemabancario.application.dto;

public class CreateAccountDto {
    public final String clientId;
    public final String agencyNumber;
    public final int limit;
    public final int balance;
    public final int accountType;

    public CreateAccountDto(
            final String clientId,
            final String agencyNumber,
            final int limit,
            final int balance,
            final int accountType
    ) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.limit = limit;
        this.balance = balance;
    }
}
