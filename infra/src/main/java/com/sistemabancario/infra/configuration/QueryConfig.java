package com.sistemabancario.infra.configuration;

import com.sistemabancario.infra.*;
import com.sistemabancario.infra.entity.TransactionEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class QueryConfig {

    private final AccountGateway accountGateway;
    private final ClientGateway clientGateway;
    private final TransactionGateway transactionGateway;
    private final ICSV<TransactionEntity> csvGenerate;

    public QueryConfig(
            final AccountGateway accountGateway,
            final ClientGateway clientGateway,
            final TransactionGateway transactionGateway,
            final ICSV<TransactionEntity> csvGenerate
    ) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
        this.clientGateway = Objects.requireNonNull(clientGateway);
        this.transactionGateway = Objects.requireNonNull(transactionGateway);
        this.csvGenerate = Objects.requireNonNull(csvGenerate);
    }

    @Bean
    public IQuery resolveQuery() {
        return new Query(
                transactionGateway,
                clientGateway,
                accountGateway,
                csvGenerate
        );
    }
}
