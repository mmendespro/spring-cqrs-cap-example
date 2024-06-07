package net.local.poc.bookread.adapters.http.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiErrorResponse> handleBadRequest(RuntimeException exception) {
		log.error("error validating a request {}", exception.getMessage(), exception);
		return ResponseEntity.badRequest().body(new ApiErrorResponse(exception.getMessage(), LocalDateTime.now()));
	}

    public record ApiErrorResponse(String message, LocalDateTime timestamp) {}
}