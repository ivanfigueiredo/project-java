CREATE TABLE client (
    client_id VARCHAR(36) NOT NULL PRIMARY KEY,
    cpf VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE account (
    account_id VARCHAR(36) NOT NULL PRIMARY KEY,
    client_id VARCHAR(36) NOT NULL,
    agency_number VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL UNIQUE,
    account_limit INT NOT NULL,
    balance INT NOT NULL,
    account_type VARCHAR(55) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT client_id FOREIGN KEY (client_id) REFERENCES client (client_id)
);

CREATE TABLE transaction (
    transaction_id VARCHAR(36) NOT NULL PRIMARY KEY,
    client_id VARCHAR(36) NOT NULL,
    account_id VARCHAR(36) NOT NULL,
    transaction_type VARCHAR(55) NOT NULL,
    account_id_target VARCHAR(36) NULL,
    amount INT NOT NULL,
    balance INT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT client_id FOREIGN KEY (client_id) REFERENCES client (client_id),
    CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES account (account_id)
);