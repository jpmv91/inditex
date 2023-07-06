package com.inditex.prices.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, ValidationException.class})
  public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
    String errorMessage = " Handle Illegal Argument Exception:  " + ex.getMessage();
    log.error(errorMessage);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

  @ExceptionHandler(value = { EntityNotFoundException.class})
  public ResponseEntity<String> handleEntityNotFoundException(Exception ex) {
    String errorMessage = " Handle Entity Not Found Exception:  " + ex.getMessage();
    log.error(errorMessage);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

}
