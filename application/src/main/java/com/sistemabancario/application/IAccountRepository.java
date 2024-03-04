package com.sistemabancario.application;

import com.sistemabancario.domain.Account;

import java.util.Optional;

public interface IAccountRepository {
    public void save(Account account);
    public void update(Account account);
    public Optional<Account> findAccountById(String accountId);
    public void updateMany(Account[] accounts);
}
