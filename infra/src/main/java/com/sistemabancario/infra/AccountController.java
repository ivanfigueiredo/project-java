package com.sistemabancario.infra;

import com.sistemabancario.application.ICreateAccount;
import com.sistemabancario.application.IUpdateAccountLimit;
import com.sistemabancario.application.dto.CreateAccountDto;
import com.sistemabancario.application.dto.UpdateAccountLimitDto;
import com.sistemabancario.infra.input.CreateAccountInput;
import com.sistemabancario.infra.input.UpdateAccountLimitInput;
import com.sistemabancario.infra.output.CreateAccountOutputDto;
import com.sistemabancario.infra.output.GetAccountOutputDto;
import com.sistemabancario.infra.output.UpdateAccountLimitOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AccountController implements IAccountAPI {
    private final ICreateAccount createAccount;

    private final IUpdateAccountLimit updateAccountLimit;

    private final IQuery query;

    public AccountController(
            final ICreateAccount createAccount,
            final IUpdateAccountLimit updateAccountLimit,
            final IQuery query
    ) {
        this.createAccount = Objects.requireNonNull(createAccount);
        this.query = Objects.requireNonNull(query);
        this.updateAccountLimit = Objects.requireNonNull(updateAccountLimit);
    }

    @Override
    public ResponseEntity<CreateAccountOutputDto> createAccount(final CreateAccountInput input) {
        final var dto = new CreateAccountDto(
                input.clientId(),
                input.agencyNumber(),
                input.limit(),
                input.balance(),
                input.accountType()
        );
        final var output = this.createAccount.execute(dto);

        return new ResponseEntity<>(new CreateAccountOutputDto(output.accountId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetAccountOutputDto> getAccount(final String accountId) {
        final var output = this.query.getAccount(accountId);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateAccountLimitOutputDto> updateLimitAccount(
            final String accountId,
            final UpdateAccountLimitInput input
    ) {
        final var dto = new UpdateAccountLimitDto(accountId, input.amount());
        final var output = this.updateAccountLimit.execute(dto);

        return new ResponseEntity<>(
                new UpdateAccountLimitOutputDto(output.accountId, output.limit),
                HttpStatus.OK
        );
    }
}
