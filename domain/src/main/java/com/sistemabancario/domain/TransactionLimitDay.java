package com.sistemabancario.domain;

import java.util.Optional;

public class TransactionLimitDay implements TransactionLimitHandler {
    private final int TRANSACTION_LIMIT_DAY = 2000;
    private Optional<TransactionLimitHandler> next = null;

    public TransactionLimitDay(final Optional<TransactionLimitHandler> next) {
        this.next = next;
    }

    public TransactionLimitDay() {}

    @Override
    public int handle(Transaction transaction) {
        if (transaction.isDay()) {
            return this.TRANSACTION_LIMIT_DAY;
        }
        return this.next.orElseThrow().handle(transaction);
    }
}
