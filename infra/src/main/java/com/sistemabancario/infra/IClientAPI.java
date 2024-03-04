package com.sistemabancario.infra;

import com.sistemabancario.infra.input.CreateClientInput;
import com.sistemabancario.infra.output.CreateClientOutput;
import com.sistemabancario.infra.output.GetClientOutputDto;
import com.sistemabancario.infra.output.ListAccountsOutputDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "clients")
public interface IClientAPI {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateClientOutput> createClient(@RequestBody final CreateClientInput input);

    @GetMapping(
            value = "/{clientId}"
    )
    public ResponseEntity<List<ListAccountsOutputDto>> getAccountList(@PathVariable String clientId);

    @GetMapping(
            value = "/info/{clientId}"
    )
    public ResponseEntity<GetClientOutputDto> getAccount(@PathVariable String clientId);
}
