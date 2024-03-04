package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateAccountDto;

public interface ICreateAccount {
    public void execute(CreateAccountDto dto);
}
