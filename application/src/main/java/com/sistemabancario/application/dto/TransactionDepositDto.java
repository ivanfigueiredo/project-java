package com.sistemabancario.application.dto;

public class TransactionDepositDto {
    public final String accountId;
    public final int amount;

    public TransactionDepositDto(
            final String accountId,
            final int amount
    ) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
