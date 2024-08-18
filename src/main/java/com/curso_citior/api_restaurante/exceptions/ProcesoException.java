package com.curso_citior.api_restaurante.exceptions;

import java.util.List;

public class ProcesoException extends RuntimeException {

  private final List<String> errores;

  public ProcesoException(String mensaje) {
    super(mensaje);
    this.errores = null;
  }

  public ProcesoException(String mensaje, List<String> errores) {
    super(mensaje);
    this.errores = errores;
  }

  public List<String> getErrores() {
    return errores;
  }
}
