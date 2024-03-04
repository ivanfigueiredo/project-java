package com.sistemabancario.infra;

import com.sistemabancario.infra.output.GetAccountOutputDto;
import com.sistemabancario.infra.output.GetClientOutputDto;
import com.sistemabancario.infra.output.ListAccountsOutputDto;
import com.sistemabancario.infra.output.TransactionListOutputDto;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface IQuery {
    public List<TransactionListOutputDto> listTransactions(final String accountId);

    public InputStreamResource exportTransactionList(final String accountId);

    public List<ListAccountsOutputDto> listAccounts(final String clientId);

    public GetAccountOutputDto getAccount(final String accountId);

    public GetClientOutputDto getClient(final String clientId);
}
