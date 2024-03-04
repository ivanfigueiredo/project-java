package com.sistemabancario.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Client extends Entity {
    private final String name;
    private final String email;
    private final CPFValueObject cpf;
    private final LocalDate birthDate;
    private final Instant createdAt;
    private final Instant updatedAt;

    private Client(
            final String clientId,
            final String name,
            final String email,
            final CPFValueObject cpf,
            final LocalDate birthDate,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(clientId);
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getClientId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public CPFValueObject getCpf() {
        return this.cpf;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public static Client create(final String name, final String email, final String birthDate, final String cpf) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate newBirthDate = LocalDate.parse(birthDate, format);
        String clientId = UUID.randomUUID().toString();
        CPFValueObject cpfVO = CPFValueObject.create(cpf);
        return new Client(clientId, name, email, cpfVO, newBirthDate, Instant.now(), Instant.now());
    }

    public static Client restore(
            final String clientId,
            final String name,
            final String email,
            final LocalDate birthDate,
            final Instant createdAt,
            final Instant updatedAt,
            final CPFValueObject cpf
    ) {
        return new Client(clientId, name, email, cpf, birthDate, createdAt, updatedAt);
    }
}