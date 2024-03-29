package com.sistemabancario.application;

import com.sistemabancario.application.dto.TransactionTransferDto;
import com.sistemabancario.application.dto.TransactionWithdrawDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionTransferTest {

    @InjectMocks
    private TransactionTransfer useCase;

    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private ITransactionRepository transactionRepository;


    @Test
    public void shouldTransactionTransferWithSuccess() {
        final var clientId = UUID.randomUUID().toString();
        final var agencyNumber = "4445547";
        final var limit = 500;
        final var balance = 1000;
        final var accountType = 1;
        final Account accountFrom = Account.create(clientId, agencyNumber, limit, balance, accountType);
        final Account accountTo = Account.create(clientId, agencyNumber, limit, balance, accountType);

        final var amount = 200;

        final var dto = new TransactionTransferDto(accountFrom.getAccountId(), accountTo.getAccountId(), amount);
        when(accountRepository.findAccountById(eq(accountFrom.getAccountId())))
                .thenReturn(Optional.of(accountFrom));

        when(accountRepository.findAccountById(eq(accountTo.getAccountId())))
                .thenReturn(Optional.of(accountTo));

        useCase.execute(dto);

         verify(accountRepository, times(1)).findAccountById(accountFrom.getAccountId());
         verify(accountRepository, times(1)).findAccountById(accountTo.getAccountId());

        Mockito.verify(accountRepository, times(1)).updateMany(argThat(Accounts ->
                Objects.equals(balance - amount, Arrays.stream(Accounts).toList().get(0).getBalance())
                && Objects.equals(balance + amount, Arrays.stream(Accounts).toList().get(1).getBalance())
        ));

        Mockito.verify(transactionRepository, times(1)).save(argThat(Transaction ->
                Objects.equals(accountFrom.getAccountId(), Transaction.getAccountId())
                        && Objects.equals(accountFrom.getClientId(), Transaction.getClientId())
                        && Objects.equals(amount, Transaction.getAmount())
                        && Objects.equals(accountFrom.getBalance(), Transaction.getBalance())
                        && Objects.nonNull(Transaction.getTransactionId())
                        && Objects.nonNull(Transaction.getTransactionType())
                        && Objects.equals(Transaction.getTransactionType(), TransactionType.TRANSFER.transactionType)
                        && Objects.nonNull(Transaction.getAccountIdTarget())
                        && Objects.equals(Transaction.getAccountIdTarget(), accountTo.getAccountId())
                        && Objects.nonNull(Transaction.getCreatedAt())
        ));
    }


    @Test
    public void shouldNotTransactionTransferAccountFromNotExists() {
        final var accountIdFrom = UUID.randomUUID().toString();
        final var accountIdTo = UUID.randomUUID().toString();
        final var newLimit = 200;

        final var dto = new TransactionTransferDto(accountIdFrom, accountIdTo, newLimit);

        final var expectedMessage = "AccountFrom not found";

        final var exception = Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(dto));

        verify(accountRepository, times(1)).findAccountById(eq(accountIdFrom));

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    public void shouldNotTransactionTransferAccountToNotExists() {
        final var clientId = UUID.randomUUID().toString();
        final var agencyNumber = "4445547";
        final var limit = 500;
        final var balance = 1000;
        final var accountType = 1;
        final Account accountFrom = Account.create(clientId, agencyNumber, limit, balance, accountType);

        final var accountIdTo = UUID.randomUUID().toString();
        final var newLimit = 200;

        final var dto = new TransactionTransferDto(accountFrom.getAccountId(), accountIdTo, newLimit);

        when(accountRepository.findAccountById(eq(accountFrom.getAccountId())))
                .thenReturn(Optional.of(accountFrom));

        final var expectedMessage = "AccountTo not found";

        final var exception = Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(dto));

        verify(accountRepository, times(1)).findAccountById(eq(accountFrom.getAccountId()));
        verify(accountRepository, times(1)).findAccountById(eq(accountIdTo));

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
