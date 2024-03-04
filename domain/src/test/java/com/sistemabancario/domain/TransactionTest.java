package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TransactionTest {


    @Test
    public void shouldTransactionDepositWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;
        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        transaction.depositTransaction(account);

        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getTransactionId());
        Assertions.assertNotNull(transaction.getCreatedAt());

        Assertions.assertEquals(transaction.getAmount(), amount);
        Assertions.assertEquals(transaction.getAccountId(), account.getAccountId());
        Assertions.assertEquals(transaction.getBalance(), 1000);
        Assertions.assertEquals(transaction.getClientId(), clientId);
        Assertions.assertEquals(transaction.getTransactionType(), TransactionType.DEPOSIT.transactionType);
    }

    @Test
    public void shouldTransactionDepositWithFailIfExcedLimit() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;
        final int amount = 5000;

        final String expectedErrorMessage = "Transaction exceeds permitted limit";

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        final var exception = Assertions.assertThrows(DomainException.class, () -> transaction.depositTransaction(account));

        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

    @Test
    public void shouldTransactionWithdrawWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 700;
        final int accountType = 1;
        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        transaction.withdrawalTransaction(account);

        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getTransactionId());
        Assertions.assertNotNull(transaction.getCreatedAt());

        Assertions.assertEquals(transaction.getAmount(), amount);
        Assertions.assertEquals(transaction.getAccountId(), account.getAccountId());
        Assertions.assertEquals(transaction.getBalance(), 200);
        Assertions.assertEquals(transaction.getClientId(), clientId);
        Assertions.assertEquals(transaction.getTransactionType(), TransactionType.WITHDRAW.transactionType);
    }

    @Test
    public void shouldTransactionWithdrawWithFailIfExcedLimit() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 700;
        final int accountType = 1;
        final int amount = 50000;

        final String expectedErrorMessage = "Transaction exceeds permitted limit";

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        final var exception = Assertions.assertThrows(DomainException.class, () -> transaction.withdrawalTransaction(account));

        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

    @Test
    public void shouldUpdateLimitAccountWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 700;
        final int accountType = 1;
        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        transaction.updateLimit(account);

        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getTransactionId());
        Assertions.assertNotNull(transaction.getCreatedAt());

        Assertions.assertEquals(transaction.getAmount(), amount);
        Assertions.assertEquals(transaction.getAccountId(), account.getAccountId());
        Assertions.assertEquals(transaction.getBalance(), account.getBalance());
        Assertions.assertEquals(transaction.getClientId(), clientId);
        Assertions.assertEquals(transaction.getTransactionType(), TransactionType.UPDATE_LIMIT.transactionType);
    }

    @Test
    public void shouldUpdateLimitAccountWithFailIfExcedLimit() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 700;
        final int accountType = 1;
        final int amount = 50000;

        final String expectedErrorMessage = "Transaction exceeds permitted limit";

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        final var exception = Assertions.assertThrows(DomainException.class, () -> transaction.updateLimit(account));

        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

    @Test
    public void shouldTransactionTransferWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 1000;
        final int accountType = 1;
        final int amount = 500;

        Account accountFrom = Account.create(clientId, agencyNumber, limit, balance, accountType);
        Account accountTo = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        transaction.transactionTransfer(accountFrom, accountTo);

        Assertions.assertNotNull(transaction);
        Assertions.assertNotNull(transaction.getTransactionId());
        Assertions.assertNotNull(transaction.getCreatedAt());

        Assertions.assertEquals(transaction.getAmount(), amount);
        Assertions.assertEquals(transaction.getAccountId(), accountFrom.getAccountId());
        Assertions.assertEquals(transaction.getAccountIdTarget(), accountTo.getAccountId());
        Assertions.assertEquals(transaction.getBalance(), 500);
        Assertions.assertEquals(transaction.getClientId(), clientId);
        Assertions.assertEquals(transaction.getTransactionType(), TransactionType.TRANSFER.transactionType);
    }

    @Test
    public void shouldTransactionTransferWithFailIfExcedLimit() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 1000;
        final int accountType = 1;
        final int amount = 5000;

        final String expectedErrorMessage = "Transaction exceeds permitted limit";

        Account accountFrom = Account.create(clientId, agencyNumber, limit, balance, accountType);
        Account accountTo = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Transaction transaction = Transaction.create(amount);

        final var exception = Assertions.assertThrows(DomainException.class, () -> transaction.transactionTransfer(accountFrom, accountTo));

        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
    }

}
