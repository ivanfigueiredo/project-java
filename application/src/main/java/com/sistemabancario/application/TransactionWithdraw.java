package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionWithdrawDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.Transaction;

import java.util.Objects;

public class TransactionWithdraw implements ITransactionWithdraw {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public TransactionWithdraw(final IAccountRepository accountRepository, final ITransactionRepository transactionRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }
    @Override
    public void execute(TransactionWithdrawDto dto) {
        Account account = this.accountRepository.findAccountById(dto.accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        Transaction transaction = Transaction.create(dto.amount);
        transaction.withdrawalTransaction(account);
        this.transactionRepository.save(transaction);
        this.accountRepository.update(account);
    }
}
