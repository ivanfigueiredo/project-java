package com.sistemabancario.infra.entity;

import com.sistemabancario.domain.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    private String transaction_id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "account_id_target", nullable = true)
    private String accountIdTarget;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "balance", nullable = false)
    private int balance;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    public TransactionEntity(
            final String transaction_id,
            final String accountId,
            final String accountIdTarget,
            final String clientId,
            final int amount,
            final int balance,
            final String transactionType,
            final Instant createdAt
    ) {
        this.transaction_id = transaction_id;
        this.accountId = accountId;
        this.accountIdTarget = accountIdTarget;
        this.clientId = clientId;
        this.amount = amount;
        this.balance = balance;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
    }

    public TransactionEntity() {}

    public static TransactionEntity from(final Transaction transaction) {
        return new TransactionEntity(
                transaction.getTransactionId(),
                transaction.getAccountId(),
                transaction.getAccountIdTarget(),
                transaction.getClientId(),
                transaction.getAmount(),
                transaction.getBalance(),
                transaction.getTransactionType(),
                transaction.getCreatedAt()
        );
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getTransactionType() { return transactionType; }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountIdTarget() {
        return accountIdTarget;
    }

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
}
