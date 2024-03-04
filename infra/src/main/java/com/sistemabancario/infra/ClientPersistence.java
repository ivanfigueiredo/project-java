package com.sistemabancario.infra;

import com.sistemabancario.application.IClientRepository;
import com.sistemabancario.domain.Client;
import com.sistemabancario.infra.entity.ClientEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientPersistence implements IClientRepository {
    private final ClientGateway repository;

    public ClientPersistence(final ClientGateway repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void save(Client client) {
        this.repository.save(ClientEntity.from(client));
    }

    @Override
    public Optional<Client> findClientById(final String clientId) {
        return this.repository.findById(clientId).map(ClientEntity::toDomain);
    }
}
