package co.com.nexus.api.exception;

import co.com.nexus.model.shared.exception.NexusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidation(WebExchangeBindException ex) {
        String message = ex.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.of(
                                message,
                                "Estimado socio, los datos son incorrectos",
                                HttpStatus.BAD_REQUEST,
                                LocalDateTime.now(),
                                ""
                        ))
        );
    }

    @ExceptionHandler(NexusException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNexusException(NexusException ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getHttpStatus());
        return Mono.just(
                ResponseEntity
                        .status(httpStatus)
                        .body(ErrorResponse.of(
                                ex.getMessage(),
                                "Por favor intente más tarde",
                                httpStatus,
                                LocalDateTime.now(),
                                ex.getCode() != null ? ex.getCode() : ex.getClass().getSimpleName()
                        ))
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ErrorResponse.of(
                                ex.getMessage(),
                                "Ocurrió un error inesperado. Por favor intente más tarde.",
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                LocalDateTime.now(),
                                ex.getClass().getSimpleName()
                        ))
        );
    }
}
