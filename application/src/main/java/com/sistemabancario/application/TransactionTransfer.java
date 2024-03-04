package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionTransferDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.Transaction;

import java.lang.reflect.Array;
import java.util.Objects;

public class TransactionTransfer implements ITransactionTransfer {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public TransactionTransfer(final IAccountRepository accountRepository, final ITransactionRepository transactionRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }
    @Override
    public void execute(TransactionTransferDto dto) {
        Account accountFrom = this.accountRepository.findAccountById(dto.accountIdFrom)
                .orElseThrow(() -> new NotFoundException("AccountFrom not found"));
        Account accountTo = this.accountRepository.findAccountById(dto.accountIdTo)
                .orElseThrow(() -> new NotFoundException("AccountTo not found"));
        Transaction transaction = Transaction.create(dto.amount);
        transaction.transactionTransfer(accountFrom, accountTo);
        this.transactionRepository.save(transaction);
        Account[] accounts = {accountFrom, accountTo};
        this.accountRepository.updateMany(accounts);
    }
}
