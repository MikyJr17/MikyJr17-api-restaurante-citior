package com.curso_citior.api_restaurante.pedidos;

import com.curso_citior.api_restaurante.detalles.DetalleRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequest {

  @NotEmpty(message = "El nombre del cliente no puede estar vacío")
  private String clienteNombre;

  @NotNull(message = "La lista de detalles no puede ser nula")
  @NotEmpty(message = "El pedido debe contener al menos un detalle")
  private List<@Valid DetalleRequest> detalles;
}