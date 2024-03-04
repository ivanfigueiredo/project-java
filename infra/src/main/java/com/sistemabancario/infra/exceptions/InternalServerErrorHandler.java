package com.sistemabancario.infra.exceptions;

import com.sistemabancario.domain.exceptions.NoStacktraceException;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class InternalServerErrorHandler implements ApiExpcetionBaseHandler {
    private Optional<ApiExpcetionBaseHandler> next = null;

    public InternalServerErrorHandler(final Optional<ApiExpcetionBaseHandler> next) {
        this.next = next;
    }
    @Override
    public ApiExceptionBaseOutputDto handle(NoStacktraceException e) {
        if (e instanceof InternalServerErrorException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
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
