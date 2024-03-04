package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionWithdrawDto;

public interface ITransactionWithdraw {
    public void execute(TransactionWithdrawDto dto);
}
