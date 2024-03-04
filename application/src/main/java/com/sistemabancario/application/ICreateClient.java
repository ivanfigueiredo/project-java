package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateClientDto;
import com.sistemabancario.application.dto.CreateClientOutputDto;

public interface ICreateClient {
    public CreateClientOutputDto execute(final CreateClientDto dto);
}
