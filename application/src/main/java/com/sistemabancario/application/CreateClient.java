package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateClientDto;
import com.sistemabancario.application.dto.CreateClientOutputDto;
import com.sistemabancario.domain.Client;

import java.util.Objects;

public class CreateClient implements ICreateClient {
    private final IClientRepository clientRepository;

    public CreateClient(final IClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }
    @Override
    public CreateClientOutputDto execute(CreateClientDto dto) {
        Client client = Client.create(dto.name, dto.email, dto.birthDate, dto.cpf);
        this.clientRepository.save(client);
        return new CreateClientOutputDto(client.getClientId());
    }
}
