package com.curso_citior.api_restaurante.detalles;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetalleResponse {

  private UUID uuid;
  private Integer cantidad;
  private String platoNombre;
  private BigDecimal precio;
  private BigDecimal subtotal;
  private UUID platoUuid;
}
