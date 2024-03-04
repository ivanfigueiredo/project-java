package com.sistemabancario.infra.exceptions;

import com.sistemabancario.domain.exceptions.NoStacktraceException;

public class InternalServerErrorException extends NoStacktraceException {
    public InternalServerErrorException(final String aMessage) {super(aMessage);}
}
