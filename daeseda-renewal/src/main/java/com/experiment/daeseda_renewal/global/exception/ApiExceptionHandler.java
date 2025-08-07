package com.experiment.daeseda_renewal.global.exception;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleApiError(BusinessException ex) {
    return ResponseEntity.status(400)
                         .body(Map.of(
                             "code", ex.getErrorCode()
                                       .name(),
                             "message", ex.getErrorCode()
                                          .getMessage()
                         ));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleUnexpectedException(Exception ex) {
    return ResponseEntity.status(500)
                         .body(Map.of(
                             "cause", ex.getCause(),
                             "message", ex.getMessage()
                         ));
  }
}
