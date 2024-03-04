package com.sistemabancario.application.exceptions;

import com.sistemabancario.domain.exceptions.NoStacktraceException;

public class NotFoundException extends NoStacktraceException {
    public NotFoundException(final String eMessage) {
        super(eMessage);
    }
}
