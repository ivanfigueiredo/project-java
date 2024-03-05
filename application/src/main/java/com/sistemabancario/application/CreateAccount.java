package com.sistemabancario.application;

import com.sistemabancario.application.dto.CreateAccountDto;
import com.sistemabancario.application.dto.CreateAccountOutputDto;
import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.Account;
import com.sistemabancario.domain.Client;
import com.sistemabancario.domain.exceptions.InternalServerErrorException;

import java.util.Objects;

public class CreateAccount implements ICreateAccount {
    private final IAccountRepository accountRepository;
    private final IClientRepository clientRepository;

    public CreateAccount(
            final IAccountRepository accountRepository,
            final IClientRepository clientRepository
    ) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public CreateAccountOutputDto execute(final CreateAccountDto dto) {
        try {
            Client client = this.clientRepository.findClientById(dto.clientId)
                    .orElseThrow(() -> new NotFoundException("Client not found"));
            Account account = Account.create(client.getClientId(), dto.agencyNumber, dto.limit, dto.balance, dto.accountType);
            this.accountRepository.save(account);

            return new CreateAccountOutputDto(account.getAccountId());
        } catch (InternalServerErrorException e) {
            throw e;
        }
    }
}
