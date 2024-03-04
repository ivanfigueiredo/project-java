package com.sistemabancario.application;

import com.sistemabancario.application.dto.UpdateAccountLimitDto;
import com.sistemabancario.application.dto.UpdateAccountLimitOutputDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.Transaction;

import java.util.Objects;

public class UpdateAccountLimit implements IUpdateAccountLimit {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public UpdateAccountLimit(final IAccountRepository accountRepository, final ITransactionRepository transactionRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }
    @Override
    public UpdateAccountLimitOutputDto execute(UpdateAccountLimitDto dto) {
        Account account = this.accountRepository.findAccountById(dto.accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        Transaction transaction = Transaction.create(dto.limit);
        transaction.updateLimit(account);
        this.transactionRepository.save(transaction);
        this.accountRepository.update(account);
        return new UpdateAccountLimitOutputDto(account.getAccountId(), account.getLimit());
    }
}
