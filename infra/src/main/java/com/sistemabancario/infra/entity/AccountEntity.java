package com.sistemabancario.infra.entity;

import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.AccountType;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    private String account_id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "agency_number", nullable = false)
    private String agencyNumber;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "account_limit", nullable = false)
    private int limit;

    @Column(name = "balance", nullable = false)
    private int balance;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    public AccountEntity(
            final String accountId,
            final String clientId,
            final String accountNumber,
            final String agencyNumber,
            final Instant createdAt,
            final int limit,
            final int balance,
            final Instant updatedAt,
            final String accountType
    ) {
        this.account_id = accountId;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.createdAt = createdAt;
        this.limit = limit;
        this.balance = balance;
        this.updatedAt = updatedAt;
        this.accountType = accountType;
    }

    public AccountEntity() {}

    public static AccountEntity from(Account account) {
        return new AccountEntity(
                account.getAccountId(),
                account.getClientId(),
                account.getAccountNumber(),
                account.getAgencyNumber(),
                account.getCreatedAt(),
                account.getLimit(),
                account.getBalance(),
                account.getUpdatedAt(),
                account.getAccountType()
        );
    }

    public Account toDomain() {
        return Account.restore(
            this.account_id,
            this.clientId,
            this.accountNumber,
            this.agencyNumber,
            this.limit,
            this.balance,
            this.accountType,
            this.createdAt,
            this.updatedAt
        );
    }

    public String getAccountId() {
        return account_id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public int getLimit() {
        return limit;
    }

    public int getBalance() {
        return balance;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getAccountType() {
        return accountType;
    }
}
