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

    public CommandLineInterface(final ICreateAccount createAccount,
                                final ITransactionDeposit transactionDeposit,
                                final ITransactionWithdraw transactionWithdraw,
                                final ITransactionTransfer transactionTransfer,
                                final IUpdateAccountLimit updateAccountLimit,
                                final ICreateClient createClient) {
        this.createAccount = Objects.requireNonNull(createAccount);
        this.transactionDeposit = Objects.requireNonNull(transactionDeposit);
        this.transactionWithdraw = Objects.requireNonNull(transactionWithdraw);
        this.transactionTransfer = Objects.requireNonNull(transactionTransfer);
        this.updateAccountLimit = Objects.requireNonNull(updateAccountLimit);
        this.createClient = Objects.requireNonNull(createClient);
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
            this.createClient.execute(createClientDto);
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
            this.createAccount.execute(createAccountDto);
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
            this.updateAccountLimit.execute(updateAccountLimitDto);
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
}
