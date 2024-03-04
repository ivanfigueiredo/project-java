package com.sistemabancario.infra;

import com.sistemabancario.infra.input.TransactionDepositInput;
import com.sistemabancario.infra.input.TransactionTransferInput;
import com.sistemabancario.infra.input.TransactionWithdrawInput;
import com.sistemabancario.infra.output.TransactionListOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "transactions")
public interface ITransactionAPI {

    @PostMapping(
            value = "deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transactionDeposit(@RequestBody final TransactionDepositInput input);

    @PostMapping(
            value = "withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transactionWithdraw(@RequestBody final TransactionWithdrawInput input);

    @PostMapping(
            value = "transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transactionTransfer(@RequestBody final TransactionTransferInput input);

    @GetMapping(
            value = "/export-csv/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Resource> exportCSV(@PathVariable String accountId);

    @GetMapping(
            value = "/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TransactionListOutputDto>> listTransactions(@PathVariable String accountId);
}
