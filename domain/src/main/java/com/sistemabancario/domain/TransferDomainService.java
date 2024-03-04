package com.sistemabancario.domain;

public class TransferDomainService {
    public static void transfer(Account from, Account to, int amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
