package com.sistemabancario.application.dto;

public class CreateClientDto {
    public final String name;
    public final String email;
    public final String birthDate;
    public final String cpf;

    public CreateClientDto(final String name,
                           final String email,
                           final String birthDate,
                           final String cpf) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }
}
