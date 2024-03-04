package com.sistemabancario.infra.configuration;

import com.sistemabancario.infra.CSVTransaction;
import com.sistemabancario.infra.ICSV;
import com.sistemabancario.infra.entity.TransactionEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVConfig {
    @Bean
    public ICSV<TransactionEntity> resolveDependency() {
        return new CSVTransaction();
    }
}
