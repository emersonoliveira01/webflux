package com.example.webfluxcourse.controller.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(DuplicateKeyException exception,
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
}
