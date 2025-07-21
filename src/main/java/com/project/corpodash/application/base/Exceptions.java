package com.project.corpodash.application.base;

import jakarta.persistence.EntityExistsException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class Exceptions extends ResponseEntityExceptionHandler {

  @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex, WebRequest request) {
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler({EntityExistsException.class})
  protected ResponseEntity<ErrorResponse> handleExists(
      EntityExistsException ex, WebRequest request) {
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler({BadRequestException.class})
  protected ResponseEntity<ErrorResponse> handleExists(BadRequestException ex, WebRequest request) {
    return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
    ErrorResponse error =
        new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message != null ? message : "Unexpected error");
    return new ResponseEntity<>(error, status);
  }
}
