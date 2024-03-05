package com.sistemabancario.infra;

import com.sistemabancario.infra.input.CreateAccountInput;
import com.sistemabancario.infra.input.UpdateAccountLimitInput;
import com.sistemabancario.infra.output.CreateAccountOutputDto;
import com.sistemabancario.infra.output.GetAccountOutputDto;
import com.sistemabancario.infra.output.UpdateAccountLimitOutputDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "accounts")
public interface IAccountAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateAccountOutputDto> createAccount(@RequestBody  final CreateAccountInput input);

    @GetMapping(
            value = "/{accountId}"
    )
    public ResponseEntity<GetAccountOutputDto> getAccount(@PathVariable String accountId);

    @PatchMapping(
            value = "/{accountId}/limit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateAccountLimitOutputDto> updateLimitAccount(
            @PathVariable String accountId,
            @RequestBody UpdateAccountLimitInput input
    );
}
