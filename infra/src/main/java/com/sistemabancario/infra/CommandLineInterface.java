package com.sistemabancario.infra;

import com.sistemabancario.application.*;
import com.sistemabancario.application.dto.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Objects;

@ShellComponent
public class CommandLineInterface {

    private final ICreateAccount createAccount;
    private final ICreateClient createClient;
    private final ITransactionDeposit transactionDeposit;
    private final ITransactionWithdraw transactionWithdraw;
    private final ITransactionTransfer transactionTransfer;
    private final IUpdateAccountLimit updateAccountLimit;
    private final IQuery query;

    public CommandLineInterface(final ICreateAccount createAccount,
                                final ITransactionDeposit transactionDeposit,
                                final ITransactionWithdraw transactionWithdraw,
                                final ITransactionTransfer transactionTransfer,
                                final IUpdateAccountLimit updateAccountLimit,
                                final ICreateClient createClient,
                                final IQuery query) {
        this.createAccount = Objects.requireNonNull(createAccount);
        this.transactionDeposit = Objects.requireNonNull(transactionDeposit);
        this.transactionWithdraw = Objects.requireNonNull(transactionWithdraw);
        this.transactionTransfer = Objects.requireNonNull(transactionTransfer);
        this.updateAccountLimit = Objects.requireNonNull(updateAccountLimit);
        this.createClient = Objects.requireNonNull(createClient);
        this.query = Objects.requireNonNull(query);
    }

    @ShellMethod(key = "create-client", value = "Create Client", group = "Component")
    public void createClient(
            @ShellOption(defaultValue = "User Default") String name,
            @ShellOption(defaultValue = "default@mail.com") String email,
            @ShellOption(defaultValue = "2000/10/05") String birthDate,
            @ShellOption(defaultValue = "88845712466") String cpf
    ) {
        try {
            String[] editName = name.split("_");
            String newName = editName[0] + " " + editName[1];
            final CreateClientDto createClientDto = new CreateClientDto(newName,
                    email,
                    birthDate,
                    cpf);
            final var output = this.createClient.execute(createClientDto);
            final var response = String.format("\nClientId: %s", output.clientId);
            System.out.println(response);
        } catch (final RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "create-account", value = "Create Account", group = "Component")
    public void createAccount(
            @ShellOption(defaultValue = "88787877878") String clientId,
            @ShellOption(defaultValue = "44474747") String agencyNumber,
            @ShellOption(defaultValue = "1000") Integer limit,
            @ShellOption(defaultValue = "500") Integer balance,
            @ShellOption(defaultValue = "1") Integer accountType
    ) {
        try {
            final CreateAccountDto createAccountDto = new CreateAccountDto(clientId,
                    agencyNumber,
                    limit,
                    balance,
                    accountType);
            final var output = this.createAccount.execute(createAccountDto);
            final var response = String.format("\nAccountId: %s", output.accountId);
            System.out.println(response);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "update-account-limit", value = "Update Account Limit", group = "Component")
    public void updateAccountLimit(
            @ShellOption(defaultValue = "accountId") String accountId,
            @ShellOption(defaultValue = "1000") Integer limit
    ) {
        try {
            final UpdateAccountLimitDto updateAccountLimitDto = new UpdateAccountLimitDto(accountId, limit);
            final var output = this.updateAccountLimit.execute(updateAccountLimitDto);
            final var response = String.format("\nAccountId: %s\nLimit: %d", output.accountId, output.limit);
            System.out.println(response);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "transaction-deposit", value = "Transaction Deposit", group = "Component")
    public void transactionDeposit(
            @ShellOption(defaultValue = "accountId") String accountId,
            @ShellOption(defaultValue = "1000") Integer amount
    ) {
        try {
            final TransactionDepositDto transactionDepositDto = new TransactionDepositDto(accountId, amount);
            this.transactionDeposit.execute(transactionDepositDto);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "transaction-withdraw", value = "Transaction Withdraw", group = "Component")
    public void transactionWithdraw(
            @ShellOption(defaultValue = "accountId") String accountId,
            @ShellOption(defaultValue = "1000") Integer amount
    ) {
        try {
            final TransactionWithdrawDto transactionWithdrawDto = new TransactionWithdrawDto(accountId, amount);
            this.transactionWithdraw.execute(transactionWithdrawDto);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "transaction-transfer", value = "Transaction Transfer", group = "Component")
    public void transactionTransfer(
            @ShellOption(defaultValue = "accountFrom") String accountIdFrom,
            @ShellOption(defaultValue = "accountTo") String accountIdTo,
            @ShellOption(defaultValue = "1000") Integer amount
    ) {
        try {
            final TransactionTransferDto transactionTransferDto = new TransactionTransferDto(accountIdFrom, accountIdTo, amount);
            this.transactionTransfer.execute(transactionTransferDto);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "getAccount", value = "Account Detail", group = "Component")
    public void getAccount(
            @ShellOption(defaultValue = "accountId") String accountId
    ) {
        try {
            final var output = this.query.getAccount(accountId);
            final var response = String.
                    format("\nAccountId: %s\nClientId: %s\nAccountNumber: %s\nAgencyNumber: %s\nAccountType: %s\nBalance: %d\nLimit: %d\n",
                            output.accountId(),
                            output.clientId(),
                            output.accountNumber(),
                            output.agencyNumber(),
                            output.accountType(),
                            output.balance(),
                            output.limit()
                    );
            System.out.println(response);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "getClient", value = "Client Detail", group = "Component")
    public void getClient(
            @ShellOption(defaultValue = "clientId") String clientId
    ) {
        try {
            final var output = this.query.getClient(clientId);
            final var response = String.
                    format("\nClientId: %s\nName: %s\nEmail: %s\nCPF: %s\nBirthDate: %s",
                            output.clientId(),
                            output.name(),
                            output.email(),
                            output.cpf(),
                            output.birthDate()
                    );
            System.out.println(response);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "list-transactions", value = "List Transactions", group = "Component")
    public void listTransactions(
            @ShellOption(defaultValue = "accountId") String accountId
    ) {
        try {
            final var output = this.query.listTransactions(accountId);
            for (var item: output) {
                final var response = String.
                        format("\nTransactionId: %s\nAccountId: %s\nClientId: %s\nTransactionType: %s\nBalance: %d\nAmount: %d\nOwner: %s\nAccountIdTarget: %s",
                                item.transactionId(),
                                item.accountId(),
                                item.clientId(),
                                item.transactionType(),
                                item.balance(),
                                item.amount(),
                                item.owner(),
                                item.accountIdTarget().orElse("NULL")
                        );
                System.out.println(response);
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @ShellMethod(key = "list-accounts", value = "List Accounts", group = "Component")
    public void listAccounts(
            @ShellOption(defaultValue = "clientId") String clientId
    ) {
        try {
            final var output = this.query.listAccounts(clientId);
            for (var item: output) {
                final var response = String.
                        format("\nAccountId: %s\nClientId: %s\nAccountNumber: %s\nAgencyNumber: %s\nAccountType: %s\nBalance: %d\nLimit: %d\n",
                                item.accountId(),
                                item.clientId(),
                                item.accountNumber(),
                                item.agencyNumber(),
                                item.accountType(),
                                item.balance(),
                                item.limit()
                        );
                System.out.println(response);
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
