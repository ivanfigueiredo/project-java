package com.sistemabancario.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TransferDomainServiceTest {


    @Test
    public void shouldTransferWithSuccess() {
        final String clientId = UUID.randomUUID().toString();
        final String agencyNumber = String.valueOf(Math.random()).substring(8);
        final String OtherAgencyNumber = String.valueOf(Math.random()).substring(8);
        final int limit = 1000;
        final int balance = 1000;
        final int accountType = 1;

        final int amount = 500;

        Account accountFrom = Account.create(clientId, agencyNumber, limit, balance, accountType);
        Account accountTo = Account.create(clientId, OtherAgencyNumber, limit, balance, accountType);

        Assertions.assertNotNull(accountFrom);
        Assertions.assertNotNull(accountTo);

        Assertions.assertEquals(accountFrom.getBalance(), balance);
        Assertions.assertEquals(accountTo.getBalance(), balance);

        TransferDomainService.transfer(accountFrom, accountTo, amount);

        Assertions.assertNotEquals(accountFrom.getBalance(), balance);
        Assertions.assertNotEquals(accountTo.getBalance(), balance);


        Assertions.assertEquals(accountFrom.getBalance(), 500);
        Assertions.assertEquals(accountTo.getBalance(), 1500);
    }
}
