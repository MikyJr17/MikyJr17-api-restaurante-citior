package com.curso_citior.api_restaurante.exceptions;

import java.util.List;

public class ExcepcionResponse {

  private String mensaje;
  private List<String> detalles;

  public ExcepcionResponse(String mensaje) {
    this.mensaje = mensaje;
  }

  public ExcepcionResponse(String mensaje, List<String> detalles) {
    this.mensaje = mensaje;
    this.detalles = detalles;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public List<String> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<String> detalles) {
    this.detalles = detalles;
  }
}
