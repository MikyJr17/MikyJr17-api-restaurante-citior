package com.curso_citior.api_restaurante.detalles;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleRequest {

  @NotNull(message = "El UUID del plato es obligatorio")
  private UUID platoUuid;

  @NotNull(message = "La cantidad es obligatoria")
  @Min(value = 1, message = "La cantidad debe ser al menos 1")
  private Integer cantidad;
}
