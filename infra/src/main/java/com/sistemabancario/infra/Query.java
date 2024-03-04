package com.sistemabancario.infra;

import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.infra.entity.AccountEntity;
import com.sistemabancario.infra.entity.ClientEntity;
import com.sistemabancario.infra.entity.TransactionEntity;
import com.sistemabancario.infra.output.GetAccountOutputDto;
import com.sistemabancario.infra.output.GetClientOutputDto;
import com.sistemabancario.infra.output.ListAccountsOutputDto;
import com.sistemabancario.infra.output.TransactionListOutputDto;
import org.springframework.core.io.InputStreamResource;

import java.util.*;

public class Query implements IQuery {

    private final TransactionGateway transactionGateway;
    private final ClientGateway clientGateway;
    private final AccountGateway accountGateway;
    private final ICSV<TransactionEntity> csvGenerate;

    public Query(final TransactionGateway transactionGateway,
                 final ClientGateway clientGateway,
                 final AccountGateway accountGateway,
                 final ICSV<TransactionEntity> csvGenerate) {
        this.transactionGateway = Objects.requireNonNull(transactionGateway);
        this.clientGateway = Objects.requireNonNull(clientGateway);
        this.accountGateway = Objects.requireNonNull(accountGateway);
        this.csvGenerate = Objects.requireNonNull(csvGenerate);
    }
    @Override
    public List<TransactionListOutputDto> listTransactions(final String accountId) {
        List<TransactionEntity> transactions = this.transactionGateway
                .findAll()
                .stream()
                .filter(tran -> tran.getAccountId().equals(accountId))
                .toList();

        final String clientId = transactions.get(0).getClientId();
        ClientEntity client = this.clientGateway.findById(clientId).orElseThrow();
        return transactions
                .stream()
                .map(tran ->
                        new TransactionListOutputDto(tran.getTransaction_id(),
                            client.getName(),
                            tran.getAccountId(),
                            tran.getClientId(),
                            tran.getBalance(),
                            tran.getAmount(),
                            tran.getTransactionType(),
                            tran.getCreatedAt(),
                            Optional.ofNullable(tran.getAccountIdTarget())
                        )
                )
                .toList();
    }

    @Override
    public InputStreamResource exportTransactionList(final String accountId) {
        List<TransactionEntity> transactions = this.transactionGateway
                .findAll()
                .stream()
                .filter(tran -> tran.getAccountId().equals(accountId)).toList();
        final var output = this.csvGenerate.load(transactions);

        return new InputStreamResource(output);
    }

    @Override
    public List<ListAccountsOutputDto> listAccounts(final String clientId) {
        List<AccountEntity> accounts = this.accountGateway
                .findAll()
                .stream()
                .filter(tran -> tran.getClientId().equals(clientId)).toList();

        return accounts.stream().map(account -> new ListAccountsOutputDto(account.getAccountId(),
                account.getClientId(),
                account.getAgencyNumber(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getLimit(),
                account.getAccountType(),
                account.getCreatedAt())).toList();
    }

    @Override
    public GetAccountOutputDto getAccount(String accountId) {
        final var account = this.accountGateway
                .findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));

        return new GetAccountOutputDto(account.getAccountId(),
                account.getClientId(),
                account.getAgencyNumber(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getLimit(),
                account.getAccountType(),
                account.getCreatedAt());
    }

    @Override
    public GetClientOutputDto getClient(String clientId) {
        final var client = this.clientGateway
                .findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        return new GetClientOutputDto(client.getClient_id(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getBirthdate(),
                client.getCreatedAt(),
                client.getUpdatedAt());
    }
}
