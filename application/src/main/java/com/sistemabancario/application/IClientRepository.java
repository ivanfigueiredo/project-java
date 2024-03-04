package com.sistemabancario.application;

import com.sistemabancario.domain.Client;

import java.util.Optional;

public interface IClientRepository {
    public void save(final Client client);
    public Optional<Client> findClientById(final String clientId);
}
