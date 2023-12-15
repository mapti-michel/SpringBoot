package br.acc.webflux.controller.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(DuplicateKeyException ex, ServerHttpRequest request){
        return ResponseEntity.badRequest()
                .body(
                        Mono.just(
                                StandardError.builder()
                                        .timestamp(now())
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                        .message(verifyDupKey(ex.getMessage()))
                                        .path(request.getPath().toString())
                                        .build()
                        )
                );
    }

    private String verifyDupKey(String message){
        if(message.contains("user dup key")){
            return "User already registered";
        }
        return "Dup key exception";
    }

}
