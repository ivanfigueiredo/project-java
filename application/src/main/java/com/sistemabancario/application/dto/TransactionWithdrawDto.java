package com.sistemabancario.application.dto;

public class TransactionWithdrawDto {
    public final String accountId;
    public final int amount;

    public TransactionWithdrawDto(final String accountId, final int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
