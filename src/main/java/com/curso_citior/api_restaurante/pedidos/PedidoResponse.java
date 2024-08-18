package com.curso_citior.api_restaurante.pedidos;

import com.curso_citior.api_restaurante.detalles.DetalleResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {

  private UUID uuid;
  private Integer numeroComprobante;
  private String clienteNombre;
  private LocalDateTime fechaPedido;
  private List<DetalleResponse> detalles;
  private BigDecimal total;
}
