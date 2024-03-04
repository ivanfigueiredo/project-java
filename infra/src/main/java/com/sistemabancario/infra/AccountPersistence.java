package com.sistemabancario.infra;

import com.sistemabancario.application.IAccountRepository;
import com.sistemabancario.domain.Account;
import com.sistemabancario.infra.entity.AccountEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountPersistence implements IAccountRepository {
    private final AccountGateway repository;

    public AccountPersistence(final AccountGateway repository) {
        this.repository = repository;
    }
    @Override
    public void save(final Account account) {
        this.saveOrUpdate(AccountEntity.from(account));
    }

    @Override
    public void update(final Account account) {
        this.saveOrUpdate(AccountEntity.from(account));
    }

    @Override
    public Optional<Account> findAccountById(final String accountId) {
        return this.repository.findById(accountId)
                    .map(AccountEntity::toDomain);
    }

    @Override
    public void updateMany(final Account[] accounts) {
        for (Account account : accounts) {
            this.saveOrUpdate(AccountEntity.from(account));
        }
    }

    private void saveOrUpdate(AccountEntity accountEntity) {
        this.repository.save(accountEntity);
    }
}
