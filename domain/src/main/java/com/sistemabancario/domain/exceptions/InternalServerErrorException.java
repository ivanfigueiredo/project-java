package com.sistemabancario.domain.exceptions;

public class InternalServerErrorException extends NoStacktraceException {
    public InternalServerErrorException(final String aMessage) {super(aMessage);}
}
