package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionTransferDto;

public interface ITransactionTransfer {
    public void execute(TransactionTransferDto dto);
}
