package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateAccountDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Client;
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

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAccountTest {

    @InjectMocks
    private CreateAccount useCase;

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private IAccountRepository accountRepository;

    @Test
    public void shouldCreateAAccountWithSuccess() {
        final var name = "User Test";
        final var email = "test@mail.com";
        final var cpf = "45794479051";
        final var birthDate = "1998/08/23";
        final Client client = Client.create(name, email, birthDate, cpf);


        final var expectedAgencyNumber = "88745472";
        final var expectedLimit = 500;
        final var expectedBalance = 1000;
        final var expectedAccountType = 1;
        final var expectedClientId = client.getClientId();
        final var dto = new CreateAccountDto(expectedClientId, expectedAgencyNumber, expectedLimit, expectedBalance, expectedAccountType);

        when(clientRepository.findClientById(eq(client.getClientId())))
                .thenReturn(Optional.of(client));

        final var output = useCase.execute(dto);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.accountId);

        verify(clientRepository, times(1)).findClientById(eq(client.getClientId()));
        Mockito.verify(accountRepository, times(1)).save(argThat(Account ->
                Objects.equals(expectedBalance, Account.getBalance())
                        && Objects.nonNull(Account.getAccountNumber())
                        && Objects.nonNull(Account.getAccountId())
                        && Objects.equals(expectedLimit, Account.getLimit())
                        && Objects.equals(expectedAgencyNumber, Account.getAgencyNumber())
                        && Objects.nonNull(Account.getAccountType())
                        && Objects.nonNull(Account.getCreatedAt())
                        && Objects.nonNull(Account.getUpdatedAt())
        ));
    }
}
