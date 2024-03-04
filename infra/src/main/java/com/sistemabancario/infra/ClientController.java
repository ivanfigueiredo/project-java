package com.sistemabancario.infra;

import com.sistemabancario.application.ICreateClient;
import com.sistemabancario.application.dto.CreateClientDto;
import com.sistemabancario.application.dto.CreateClientOutputDto;
import com.sistemabancario.infra.input.CreateClientInput;
import com.sistemabancario.infra.output.CreateClientOutput;
import com.sistemabancario.infra.output.GetClientOutputDto;
import com.sistemabancario.infra.output.ListAccountsOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
public class ClientController implements IClientAPI {
    private final ICreateClient createClient;
    private final IQuery query;

    public ClientController(
            final ICreateClient createClient,
            final IQuery query
    ) {
        this.createClient = Objects.requireNonNull(createClient);
        this.query = query;
    }

    @Override
    public ResponseEntity<CreateClientOutput> createClient(final CreateClientInput input) {
        CreateClientDto dto = new CreateClientDto(input.name(), input.email(), input.birthDate(), input.cpf());
        CreateClientOutputDto output = this.createClient.execute(dto);

        CreateClientOutput createClientOutput = new CreateClientOutput(output.clientId);

        return new ResponseEntity<>(createClientOutput, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ListAccountsOutputDto>> getAccountList(final String clientId) {
        final var output = this.query.listAccounts(clientId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetClientOutputDto> getAccount(String clientId) {
        final var output = this.query.getClient(clientId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
