package com.curso_citior.api_restaurante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExcepcionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExcepcionResponse> handleException(Exception e) {
    ExcepcionResponse excepcionResponse = new ExcepcionResponse(e.getMessage());
    return new ResponseEntity<>(excepcionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ProcesoException.class)
  public final ResponseEntity<ExcepcionResponse> handleAppException(ProcesoException pe) {
    ExcepcionResponse excepcionResponse;
    if (pe.getErrores() != null && !pe.getErrores().isEmpty()) {
      excepcionResponse = new ExcepcionResponse(pe.getMessage(), pe.getErrores());
    } else {
      excepcionResponse = new ExcepcionResponse(pe.getMessage());
    }
    return new ResponseEntity<>(excepcionResponse, HttpStatus.CONFLICT);
  }
}
