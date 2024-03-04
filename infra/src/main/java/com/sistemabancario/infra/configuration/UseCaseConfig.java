package com.sistemabancario.infra.configuration;

import com.sistemabancario.application.*;
import com.sistemabancario.infra.AccountPersistence;
import com.sistemabancario.infra.ClientPersistence;
import com.sistemabancario.infra.TransactionPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Objects;

@Configuration
public class UseCaseConfig {
    private final AccountPersistence accountPersistence;
    private final TransactionPersistence transactionPersistence;
    private final ClientPersistence clientPersistence;

    public UseCaseConfig(
            final AccountPersistence accountPersistence,
            final TransactionPersistence transactionPersistence,
            final ClientPersistence clientPersistence
    ) {
        this.accountPersistence = Objects.requireNonNull(accountPersistence);
        this.clientPersistence = Objects.requireNonNull(clientPersistence);
        this.transactionPersistence = Objects.requireNonNull(transactionPersistence);
    }

    @Bean
    public ICreateAccount createAccount() {
        return new CreateAccount(accountPersistence, clientPersistence);
    }

    @Bean
    public ICreateClient createClient() { return new CreateClient(clientPersistence); }

    @Bean
    public IUpdateAccountLimit updateAccountLimit() {
        return new UpdateAccountLimit(accountPersistence, transactionPersistence);
    }

    @Bean
    public ITransactionDeposit transactionDeposit() {
        return new TransactionDeposit(accountPersistence, transactionPersistence);
    }

    @Bean
    public ITransactionWithdraw transactionWithdraw() {
        return new TransactionWithdraw(accountPersistence, transactionPersistence);
    }

    @Bean
    public ITransactionTransfer transactionTransfer() {
        return new TransactionTransfer(accountPersistence, transactionPersistence);
    }
}
