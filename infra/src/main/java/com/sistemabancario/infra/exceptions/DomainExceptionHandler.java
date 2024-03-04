package com.sistemabancario.infra.exceptions;

import com.sistemabancario.domain.exceptions.DomainException;
import com.sistemabancario.domain.exceptions.NoStacktraceException;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class DomainExceptionHandler implements ApiExpcetionBaseHandler {
    private Optional<ApiExpcetionBaseHandler> next = null;

    public DomainExceptionHandler(final Optional<ApiExpcetionBaseHandler> next) {
        this.next = next;
    }

    public DomainExceptionHandler() {}
    @Override
    public ApiExceptionBaseOutputDto handle(NoStacktraceException e) {
        if (e instanceof DomainException) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            ApiException apiException = new ApiException(
                    e.getMessage(),
                    e,
                    status,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            return new ApiExceptionBaseOutputDto(apiException, status);
        }
        return this.next.orElseThrow().handle(e);
    }
}
