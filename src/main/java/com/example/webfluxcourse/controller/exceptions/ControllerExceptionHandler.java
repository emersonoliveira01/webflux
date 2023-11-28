package com.example.webfluxcourse.controller.exceptions;

import com.example.webfluxcourse.service.exception.ObjectNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Mono<StandardError>> duplicateKeyException(DuplicateKeyException exception,
                                                              ServerHttpRequest request) {

        return ResponseEntity.status(BAD_REQUEST)
                .body(Mono.just(StandardError.builder()
                                .timesTemp(LocalDateTime.now())
                                .status(BAD_REQUEST.value())
                                .error(BAD_REQUEST.getReasonPhrase())
                                .path(request.getPath().toString())
                                .message("Dup Key exception.")
                        .build()));

    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Mono<StandardError>> duplicateKeyException(ObjectNotFoundException exception,
                                                                     ServerHttpRequest request) {

        return ResponseEntity.status(NOT_FOUND)
                .body(Mono.just(StandardError.builder()
                        .timesTemp(LocalDateTime.now())
                        .status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase())
                        .path(request.getPath().toString())
                        .message(exception.getMessage())
                        .build()));

    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Mono<ValidationError>> validationError(WebExchangeBindException exception,
                                                                 ServerHttpRequest request) {

        ValidationError validationError = new ValidationError(LocalDateTime.now(), request.getPath().toString(),
                BAD_REQUEST.value(), "Validation error", "Error on validation attributes");

        for (FieldError error: exception.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(BAD_REQUEST).body(Mono.just(validationError));

    }
}
