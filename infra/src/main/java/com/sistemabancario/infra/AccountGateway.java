package com.sistemabancario.infra;

import com.sistemabancario.infra.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountGateway extends JpaRepository<AccountEntity, String> {
}
