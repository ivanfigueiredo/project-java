package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionDepositDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.Transaction;

import java.util.NoSuchElementException;
import java.util.Objects;

public class TransactionDeposit implements ITransactionDeposit {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public TransactionDeposit(final IAccountRepository accountRepository, final ITransactionRepository transactionRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }
    @Override
    public void execute(TransactionDepositDto dto) {
        Account account = this.accountRepository.findAccountById(dto.accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        Transaction transaction = Transaction.create(dto.amount);
        transaction.depositTransaction(account);

        this.accountRepository.update(account);
        this.transactionRepository.save(transaction);
    }
}
