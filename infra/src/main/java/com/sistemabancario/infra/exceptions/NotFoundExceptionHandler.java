package com.sistemabancario.infra.exceptions;

import com.sistemabancario.application.exceptions.NotFoundException;
import com.sistemabancario.domain.exceptions.DomainException;
import com.sistemabancario.domain.exceptions.NoStacktraceException;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class NotFoundExceptionHandler implements ApiExpcetionBaseHandler {
    private Optional<ApiExpcetionBaseHandler> next = null;

    public NotFoundExceptionHandler(final Optional<ApiExpcetionBaseHandler> next) {
        this.next = next;
    }

    public NotFoundExceptionHandler() {}
    @Override
    public ApiExceptionBaseOutputDto handle(NoStacktraceException e) {
        if (e instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
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
