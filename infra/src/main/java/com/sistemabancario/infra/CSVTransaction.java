package com.sistemabancario.infra;

import com.sistemabancario.infra.entity.TransactionEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVTransaction implements ICSV<TransactionEntity> {
    @Override
    public ByteArrayInputStream load(List<TransactionEntity> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (TransactionEntity item : data) {
                List<String> list = Arrays.asList(
                        item.getTransaction_id(),
                        item.getAccountId(),
                        item.getAccountIdTarget(),
                        item.getTransactionType(),
                        item.getClientId(),
                        String.valueOf(item.getAmount()),
                        String.valueOf(item.getBalance())
                );

                csvPrinter.printRecord(list);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
