package com.curso_citior.api_restaurante.platos;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlatoResponse {

  private UUID uuid;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Boolean disponible;
}
