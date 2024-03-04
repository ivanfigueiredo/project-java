package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionDepositDto;

import java.util.NoSuchElementException;

public interface ITransactionDeposit {
    public void execute(TransactionDepositDto dto);
}
