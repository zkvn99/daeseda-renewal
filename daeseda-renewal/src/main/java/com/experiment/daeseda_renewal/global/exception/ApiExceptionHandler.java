package com.experiment.daeseda_renewal.global.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Map<String, Object>> handleApiError(BusinessException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now()
                                 .toString());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("code", ex.getErrorCode()
                       .name());
    body.put("message", String.valueOf(ex.getErrorCode()
                                         .getMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                         .body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleUnexpectedException(Exception ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now()
                                 .toString());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "Internal Server Error");
    body.put("message", String.valueOf(ex.getMessage()));
    body.put("cause", String.valueOf(ex.getCause()));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                         .body(body);
  }
}
