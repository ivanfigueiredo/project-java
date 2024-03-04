package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.NoStacktraceException;

import java.util.Optional;

public interface TransactionLimitHandler {
    Optional<TransactionLimitHandler> next = null;

    public int handle(Transaction transaction);
}
