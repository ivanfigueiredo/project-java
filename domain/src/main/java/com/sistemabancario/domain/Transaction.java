package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;
import org.jetbrains.annotations.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public class Transaction extends Entity {
    private final TransactionLimitHandler transactionLimitHandler;


    private String transactionType;
    private String accountId;
    private String accountIdTarget;
    private String clientId;
    private final int amount;
    private int balance;
    private Instant createdAt;

    private Transaction(final String transactionId, final int amount) {
        super(transactionId);
        this.amount = amount;
        TransactionLimitDay transactionLimitDay = new TransactionLimitDay();
        this.transactionLimitHandler = new TransactionLimitNight(Optional.of(transactionLimitDay));
    }


    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionId() {
        return this.id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountIdTarget() {return accountIdTarget; }

    public String getClientId() {
        return clientId;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    private boolean isValidTransaction() {
        int TRANSACTION_LIMIT = this.transactionLimitHandler.handle(this);
        return this.amount > TRANSACTION_LIMIT;
    }

    public void withdrawalTransaction(Account account) {
        if (this.isValidTransaction()) {
            throw new DomainException("Transaction exceeds permitted limit");
        }
        this.transactionType = TransactionType.WITHDRAW.transactionType;
        account.withdraw(this.amount);
        this.accountId = account.getAccountId();
        this.clientId = account.getClientId();
        this.balance = account.getBalance();
        this.createdAt = Instant.now();
    }

    public void updateLimit(Account account) {
        if (this.isValidTransaction()) {
            throw new DomainException("Transaction exceeds permitted limit");
        }
        this.transactionType = TransactionType.UPDATE_LIMIT.transactionType;
        this.accountId = account.getAccountId();
        this.clientId = account.getClientId();
        this.balance = account.getBalance();
        this.createdAt = Instant.now();
        account.updateLimit(this.amount);
    }

    public void transactionTransfer(@NotNull Account from, Account to) {
        if (this.isValidTransaction()) {
            throw new DomainException("Transaction exceeds permitted limit");
        }
        this.transactionType = TransactionType.TRANSFER.transactionType;
        this.clientId = from.getClientId();
        this.accountId = from.getAccountId();
        this.accountIdTarget = to.getAccountId();
        this.createdAt = Instant.now();
        TransferDomainService.transfer(from, to, this.amount);
        this.balance = from.getBalance();
    }

    public void depositTransaction(Account account) {
        if (this.isValidTransaction()) {
            throw new DomainException("Transaction exceeds permitted limit");
        }
        this.transactionType = TransactionType.DEPOSIT.transactionType;
        this.clientId = account.getClientId();
        this.accountId = account.getAccountId();
        account.deposit(this.amount);
        this.balance = account.getBalance();
        this.createdAt = Instant.now();
    }

    public static Transaction create(final int amount) {
        String transactionId = UUID.randomUUID().toString();
        return new Transaction(transactionId, amount);
    }

    public boolean isDay() {
        final int DAY = 18;
        ZonedDateTime timeNow = ZonedDateTime.now();
        return timeNow.getHour() < DAY;
    }

    public boolean isNight() {
        final int NIGHT = 19;
        ZonedDateTime timeNow = ZonedDateTime.now();
        return timeNow.getHour() > NIGHT;
    }
}
