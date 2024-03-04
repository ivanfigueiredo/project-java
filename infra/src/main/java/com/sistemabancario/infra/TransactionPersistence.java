package com.sistemabancario.infra;

import com.sistemabancario.application.ITransactionRepository;
import com.sistemabancario.domain.Transaction;
import com.sistemabancario.infra.entity.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class TransactionPersistence implements ITransactionRepository {
    private final TransactionGateway repository;

    public TransactionPersistence(final TransactionGateway repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void save(Transaction transaction) {
        this.repository.save(TransactionEntity.from(transaction));
    }
}
