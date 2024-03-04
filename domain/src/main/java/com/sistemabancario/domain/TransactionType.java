package com.sistemabancario.domain;

public enum TransactionType {
    WITHDRAW("withdraw"),
    DEPOSIT("deposit"),
    TRANSFER("transfer"),
    UPDATE_LIMIT("update_limit");

    public final String transactionType;

    TransactionType(final String transactionType) {
        this.transactionType = transactionType;
    }
}
