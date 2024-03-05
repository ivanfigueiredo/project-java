package com.sistemabancario.application;

import com.sistemabancario.application.dto.UpdateAccountLimitDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateAccountLimitTest {

    @InjectMocks
    private UpdateAccountLimit useCase;

    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private ITransactionRepository transactionRepository;


    @Test
    public void shouldUpdateAccountLimitWithSuccess() {
        final var clientId = UUID.randomUUID().toString();
        final var agencyNumber = "4445547";
        final var limit = 500;
        final var balance = 1000;
        final var accountType = 1;
        final Account account = Account.create(clientId, agencyNumber, limit, balance, accountType);

        final var newLimit = 200;

        final var dto = new UpdateAccountLimitDto(account.getAccountId(), newLimit);
        when(accountRepository.findAccountById(eq(account.getAccountId())))
                .thenReturn(Optional.of(account));

        final var output = useCase.execute(dto);

        verify(accountRepository, times(1)).findAccountById(eq(account.getAccountId()));

        Mockito.verify(accountRepository, times(1)).update(argThat(Account ->
                Objects.equals(newLimit, Account.getLimit())
        ));

        Mockito.verify(transactionRepository, times(1)).save(argThat(Transaction ->
                Objects.equals(account.getAccountId(), Transaction.getAccountId())
                        && Objects.equals(account.getClientId(), Transaction.getClientId())
                        && Objects.equals(newLimit, Transaction.getAmount())
                        && Objects.equals(account.getBalance(), Transaction.getBalance())
                        && Objects.nonNull(Transaction.getTransactionId())
                        && Objects.nonNull(Transaction.getTransactionType())
                        && Objects.isNull(Transaction.getAccountIdTarget())
                        && Objects.nonNull(Transaction.getCreatedAt())
        ));
    }


    @Test
    public void shouldNotUpdateAAccountLimitIfAccountNotExists() {
        final var accountId = UUID.randomUUID().toString();
        final var newLimit = 200;

        final var dto = new UpdateAccountLimitDto(accountId, newLimit);

        final var expectedMessage = "Account not found";

        final var exception = Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(dto));

        verify(accountRepository, times(1)).findAccountById(eq(accountId));

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
