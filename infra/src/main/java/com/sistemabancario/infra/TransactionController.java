package com.sistemabancario.infra;

import com.sistemabancario.application.ITransactionDeposit;
import com.sistemabancario.application.ITransactionTransfer;
import com.sistemabancario.application.ITransactionWithdraw;
import com.sistemabancario.application.dto.TransactionDepositDto;
import com.sistemabancario.application.dto.TransactionTransferDto;
import com.sistemabancario.application.dto.TransactionWithdrawDto;
import com.sistemabancario.infra.input.ExportCSVByAccountIdInput;
import com.sistemabancario.infra.input.TransactionDepositInput;
import com.sistemabancario.infra.input.TransactionTransferInput;
import com.sistemabancario.infra.input.TransactionWithdrawInput;
import com.sistemabancario.infra.output.TransactionListOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class TransactionController implements ITransactionAPI {
    private final ITransactionTransfer transactionTransfer;
    private final ITransactionWithdraw transactionWithdraw;
    private final ITransactionDeposit transactionDeposit;
    private final IQuery query;

    public TransactionController(
            final ITransactionTransfer transactionTransfer,
            final ITransactionWithdraw transactionWithdraw,
            final ITransactionDeposit transactionDeposit,
            final IQuery query
    ) {
        this.transactionDeposit = Objects.requireNonNull(transactionDeposit);
        this.transactionTransfer = Objects.requireNonNull(transactionTransfer);
        this.transactionWithdraw = Objects.requireNonNull(transactionWithdraw);
        this.query = Objects.requireNonNull(query);
    }

    @Override
    public void transactionDeposit(final TransactionDepositInput input) {
        final var dto = new TransactionDepositDto(input.accountId(), input.amount());
        this.transactionDeposit.execute(dto);
    }

    @Override
    public void transactionWithdraw(final TransactionWithdrawInput input) {
        final var dto = new TransactionWithdrawDto(input.accountId(), input.amount());
        this.transactionWithdraw.execute(dto);
    }

    @Override
    public void transactionTransfer(final TransactionTransferInput input) {
        final var dto = new TransactionTransferDto(input.accountIdFrom(), input.accountIdTo(), input.amount());
        this.transactionTransfer.execute(dto);
    }

    @Override
    public ResponseEntity<Resource> exportCSV(final String accountId) {
        final String filename = "extract-transactions.csv";
        final var file = this.query.exportTransactionList(accountId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @Override
    public ResponseEntity<List<TransactionListOutputDto>> listTransactions(final String accountId) {
        final var output = this.query.listTransactions(accountId);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
