package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AccountTest {

    @Test
    public void shouldCreateAccountWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(account);
        Assertions.assertNotNull(account.getAccountId());
        Assertions.assertNotNull(account.getCreatedAt());
        Assertions.assertNotNull(account.getUpdatedAt());
        Assertions.assertNotNull(account.getAccountNumber());
        Assertions.assertNotNull(account.getClientId());
        Assertions.assertNotNull(account.getAccountType());

        Assertions.assertEquals(account.getAccountType(), AccountType.CHECKING_ACCOUNT.toString());
        Assertions.assertEquals(account.getClientId(), clientId);
        Assertions.assertEquals(account.getLimit(), limit);
        Assertions.assertEquals(account.getBalance(), balance);
        Assertions.assertEquals(account.getAgencyNumber(), agencyNumber);
    }

    @Test
    public void shouldRestoreAccountWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);
        Account restAccount = Account.restore(account.getAccountId(),
                account.getClientId(),
                account.getAccountNumber(),
                account.getAgencyNumber(),
                account.getLimit(),
                account.getBalance(),
                account.getAccountType(),
                account.getCreatedAt(),
                account.getUpdatedAt());

        Assertions.assertNotNull(restAccount);

        Assertions.assertEquals(account.getAccountType(), restAccount.getAccountType());
        Assertions.assertEquals(account.getClientId(), restAccount.getClientId());
        Assertions.assertEquals(account.getLimit(), restAccount.getLimit());
        Assertions.assertEquals(account.getBalance(), restAccount.getBalance());
        Assertions.assertEquals(account.getAgencyNumber(), restAccount.getAgencyNumber());
        Assertions.assertEquals(account.getCreatedAt(), restAccount.getCreatedAt());
        Assertions.assertEquals(account.getUpdatedAt(), restAccount.getUpdatedAt());
        Assertions.assertEquals(account.getAccountId(), restAccount.getAccountId());
    }

    @Test
    public void shouldUpdateAccountLimitWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;

        final int updateAccountLimit = 700;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(account);

        Assertions.assertEquals(account.getLimit(), limit);

        account.updateLimit(updateAccountLimit);

        Assertions.assertNotEquals(account.getLimit(), limit);

    }

    @Test
    public void shoulDepositWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 500;
        final int accountType = 1;

        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(account);

        Assertions.assertEquals(account.getBalance(), balance);

        account.deposit(amount);

        Assertions.assertNotEquals(account.getBalance(), balance);
        Assertions.assertEquals(account.getBalance(), 1000);
    }

    @Test
    public void shoulWithdrawWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 1000;
        final int accountType = 1;

        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(account);

        Assertions.assertEquals(account.getBalance(), balance);

        account.withdraw(amount);

        Assertions.assertNotEquals(account.getBalance(), balance);
        Assertions.assertEquals(account.getBalance(), 500);
    }

    @Test
    public void shoulWithdrawWithThrowIfBalanceNegative() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 200;
        final int accountType = 1;

        final String expectedMessageError = "Account with insufficient balance";

        final int amount = 500;

        Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(account);

        Assertions.assertEquals(account.getBalance(), balance);

        final var exception = Assertions.assertThrows(DomainException.class, () -> account.withdraw(amount));

        Assertions.assertEquals(exception.getMessage(), expectedMessageError);

    }
}
