package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;

import java.util.Optional;

public class TransactionLimitDay implements TransactionLimitHandler {
    private Optional<TransactionLimitHandler> next = Optional.empty();

    public TransactionLimitDay(final Optional<TransactionLimitHandler> next) {
        this.next = next;
    }

    public TransactionLimitDay() {}

    @Override
    public int handle(Transaction transaction) {
        final int TRANSACTION_LIMIT_DAY = 2000;
        if (transaction.isDay()) {
            return TRANSACTION_LIMIT_DAY;
        }
        return this.next.orElseThrow(() -> new DomainException("Invalid chain")).handle(transaction);
    }
}
