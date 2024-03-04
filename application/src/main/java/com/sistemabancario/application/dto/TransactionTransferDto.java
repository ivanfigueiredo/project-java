package com.sistemabancario.application.dto;

public class TransactionTransferDto {
    public final String accountIdFrom;
    public final String accountIdTo;
    public final int amount;

    public TransactionTransferDto(
            final String accountIdFrom,
            final String accountIdTo,
            final int amount
    ) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.amount = amount;
    }
}
