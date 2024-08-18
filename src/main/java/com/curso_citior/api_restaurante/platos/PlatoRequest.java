package com.curso_citior.api_restaurante.platos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoRequest {

  @NotBlank(message = "El nombre del plato es obligatorio")
  @Size(max = 100, message = "El nombre del plato no puede exceder los 100 caracteres")
  private String nombre;

  @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
  private String descripcion;

  @NotNull(message = "El precio del plato es obligatorio")
  @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
  private BigDecimal precio;

  @NotNull(message = "El campo 'disponible' es obligatorio")
  private Boolean disponible;
}
