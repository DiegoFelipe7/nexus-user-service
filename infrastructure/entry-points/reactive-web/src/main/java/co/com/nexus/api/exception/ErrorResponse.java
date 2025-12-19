package co.com.nexus.api.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String description,
        HttpStatus status,
        LocalDateTime timestamp,
        String details
) {
    public static ErrorResponse of(String message, String description, HttpStatus status, LocalDateTime localDateTime, String details) {
        return new ErrorResponse(message, description, status, localDateTime, details);
    }
}
