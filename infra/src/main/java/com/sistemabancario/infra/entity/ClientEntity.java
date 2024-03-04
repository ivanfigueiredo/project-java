package com.sistemabancario.infra.entity;

import com.sistemabancario.domain.CPFValueObject;
import com.sistemabancario.domain.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    private String client_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public ClientEntity(
            final String client_id,
            final String name,
            final String email,
            final String cpf,
            final LocalDate birthdate,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.client_id = client_id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ClientEntity() {}

    public static ClientEntity from(final Client client) {
        return new ClientEntity(
                client.getClientId(),
                client.getName(),
                client.getEmail(),
                client.getCpf().getValue(),
                client.getBirthDate(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }

    public Client toDomain() {
        return Client.restore(
                this.getClient_id(),
                this.getName(),
                this.getEmail(),
                this.getBirthdate(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                CPFValueObject.create(this.getCpf())
        );
    }

    public String getClient_id() {
        return client_id;
    }

    public String getCpf() { return cpf; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
