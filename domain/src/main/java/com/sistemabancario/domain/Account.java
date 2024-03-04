package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class Account extends Entity {
    private final String clientId;
    private final String accountNumber;
    private final String agencyNumber;
    private int limit;
    private int balance;
    private final String accountType;
    private final Instant createdAt;
    private Instant updatedAt;

    private Account(
            final String accountId,
            final String clientId,
            final String accountNumber,
            final String agencyNumber,
            final int limit,
            final int balance,
            final String type,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(accountId);
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.limit = limit;
        this.balance = balance;
        this.accountType = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getAccountId() {
        return this.id;
    }
    public String getClientId() {
        return this.clientId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAgencyNumber() {
        return this.agencyNumber;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void updateLimit(int amount) {
        this.limit = amount;
        this.updatedAt = Instant.now();
    }

    public void withdraw(int amount) {
        if (this.isBalanceEnough(amount)) {
            throw new DomainException("Account with insufficient balance");
        }
        this.balance -= amount;
        this.updatedAt = Instant.now();
    }

    private boolean isBalanceEnough(int amount) {
        return amount > this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
        this.updatedAt = Instant.now();
    }

    public static Account create(
            final String clientId,
            final String agencyNumber,
            final int limit,
            final int balance,
            final int accountType
    ) {
        final String accountId = UUID.randomUUID().toString();
        final String accountNumber = String.valueOf(Math.random()).substring(9);
        final String accType = String.valueOf(AccountType.CHECKING_ACCOUNT.getType() == accountType ? AccountType.CHECKING_ACCOUNT : AccountType.DEPOSIT_ACCOUNT);
        final Instant createdAt = Instant.now();
        final Instant updatedAt = Instant.now();
        return new Account(accountId, clientId, accountNumber, agencyNumber, limit, balance, accType, createdAt, updatedAt);
    }

    public static Account restore(
            final String accountId,
            final String clientId,
            final String accountNumber,
            final String agencyNumber,
            final int limit,
            final int balance,
            final String type,
            final Instant createdAt,
            final Instant updatedA
    ) {
        return new Account(accountId, clientId, accountNumber, agencyNumber, limit, balance, type, createdAt, updatedA);
    }
}
