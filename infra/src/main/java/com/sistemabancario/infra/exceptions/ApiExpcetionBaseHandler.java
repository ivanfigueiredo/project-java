package com.sistemabancario.infra.exceptions;

import com.sistemabancario.domain.exceptions.NoStacktraceException;

import java.util.Optional;

public interface ApiExpcetionBaseHandler {
    Optional<ApiExpcetionBaseHandler> next = null;

    public ApiExceptionBaseOutputDto handle(NoStacktraceException e);
}
