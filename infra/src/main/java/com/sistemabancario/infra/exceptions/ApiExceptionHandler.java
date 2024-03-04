package com.sistemabancario.infra.exceptions;

import com.sistemabancario.domain.exceptions.NoStacktraceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ApiExceptionHandler {
    private final ApiExpcetionBaseHandler apiExpcetionBaseHandler;

    public ApiExceptionHandler() {
        NotFoundExceptionHandler notFoundExceptionHandler = new NotFoundExceptionHandler();
        this.apiExpcetionBaseHandler = new DomainExceptionHandler(Optional.of(notFoundExceptionHandler));
    }

    @ExceptionHandler(value = {NoStacktraceException.class})
    public ResponseEntity<Object> handleApiException(NoStacktraceException e) {
        final var output = this.apiExpcetionBaseHandler.handle(e);
        return new ResponseEntity<>(output.apiException, output.httpStatus);
    }
}
