package com.sistemabancario.infra;

import com.sistemabancario.infra.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionGateway extends JpaRepository<TransactionEntity, String> {
}
