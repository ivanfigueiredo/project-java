package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateAccountDto;
import com.sistemabancario.application.dto.CreateAccountOutputDto;

public interface ICreateAccount {
    public CreateAccountOutputDto execute(CreateAccountDto dto);
}
