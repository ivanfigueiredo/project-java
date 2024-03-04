package com.sistemabancario.application;

import com.sistemabancario.domain.Transaction;

public interface ITransactionRepository {
    public void save(Transaction transaction);
}
