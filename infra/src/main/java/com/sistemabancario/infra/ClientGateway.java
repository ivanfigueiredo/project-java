package com.sistemabancario.infra;

import com.sistemabancario.infra.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientGateway extends JpaRepository<ClientEntity, String> {
}
