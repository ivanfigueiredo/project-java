package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateClientTest {

    @InjectMocks
    private CreateClient useCase;

    @Mock
    private IClientRepository clientRepository;


    @Test
    public void shouldCreateAnClientWithSuccess() {
        final var name = "User Test";
        final var email = "test@mail.com";
        final var cpf = "45794479051";
        final var birthDate = "1998/08/23";

        final var dto = new CreateClientDto(name, email, birthDate, cpf);

        final var output = useCase.execute(dto);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.clientId);

        Mockito.verify(clientRepository, times(1)).save(argThat(Client ->
                Objects.equals(name, Client.getName())
                        && Objects.nonNull(Client.getCreatedAt())
                        && Objects.nonNull(Client.getUpdatedAt())
                        && Objects.equals(email, Client.getEmail())
                        && Objects.equals(cpf, Client.getCpf().getValue())
        ));
    }
}
