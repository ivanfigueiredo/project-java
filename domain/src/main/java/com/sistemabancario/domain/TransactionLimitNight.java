package com.sistemabancario.domain;

import java.util.Optional;

public class TransactionLimitNight implements TransactionLimitHandler {
    private Optional<TransactionLimitHandler> next = Optional.empty();

    public TransactionLimitNight(final Optional<TransactionLimitHandler> next) {
        this.next = next;
    }

    public TransactionLimitNight() {}

    @Override
    public int handle(Transaction transaction) {
        final int TRANSACTION_LIMIT_NIGHT = 500;
        if (transaction.isNight()) {
            return TRANSACTION_LIMIT_NIGHT;
        }
        return this.next.orElseThrow().handle(transaction);
    }
}
