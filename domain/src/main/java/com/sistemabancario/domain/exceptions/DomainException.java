package com.sistemabancario.domain.exceptions;

public class DomainException extends NoStacktraceException {
    public DomainException(final String aMessage) {
        super(aMessage);
    }
}
