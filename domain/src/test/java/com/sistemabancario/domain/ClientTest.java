package com.sistemabancario.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void shouldCreateClientWithSuccess() {
        final String name = "Test User";
        final String email = "test@mail.com";
        final String birthDate = "2000/09/10";
        final String cpf = "59138058014";

        Client client = Client.create(name, email, birthDate, cpf);

        Assertions.assertNotNull(client);
        Assertions.assertNotNull(client.getClientId());
        Assertions.assertNotNull(client.getName());
        Assertions.assertNotNull(client.getEmail());
        Assertions.assertNotNull(client.getBirthDate());
        Assertions.assertEquals(client.getName(), name);
        Assertions.assertEquals(client.getEmail(), email);
        Assertions.assertNotNull(client.getCreatedAt());
        Assertions.assertNotNull(client.getUpdatedAt());
        Assertions.assertEquals(client.getCpf().getValue(), cpf);
    }

    @Test
    public void shouldRestoreClientWithSuccess() {
        final String name = "Test User";
        final String email = "test@mail.com";
        final String birthDate = "2000/09/10";
        final String cpf = "59138058014";

        Client client = Client.create(name, email, birthDate, cpf);

        Client restoreClient = Client.restore(client.getClientId(),
                client.getName(),
                client.getEmail(),
                client.getBirthDate(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                CPFValueObject.create("59138058014"));

        Assertions.assertEquals(restoreClient.getEmail(), client.getEmail());
        Assertions.assertEquals(restoreClient.getClientId(), client.getClientId());
        Assertions.assertEquals(restoreClient.getName(), client.getName());
        Assertions.assertEquals(restoreClient.getBirthDate(), client.getBirthDate());
        Assertions.assertEquals(restoreClient.getUpdatedAt(), client.getUpdatedAt());
        Assertions.assertEquals(restoreClient.getCreatedAt(), client.getCreatedAt());
        Assertions.assertEquals(restoreClient.getCpf().getValue(), client.getCpf().getValue());
    }
}
