package com.curso_citior.api_restaurante.pedidos;

import com.curso_citior.api_restaurante.detalles.Detalle;
import com.curso_citior.api_restaurante.detalles.DetalleResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

  public PedidoResponse mapToResponse(Pedido pedido) {
    List<DetalleResponse> detalleResponses = new ArrayList<>();
    BigDecimal totalPedido = BigDecimal.ZERO;

    for (Detalle detalle : pedido.getDetalles()) {
      BigDecimal totalDetalle = detalle.getPlato().getPrecio()
          .multiply(BigDecimal.valueOf(detalle.getCantidad()));
      totalPedido = totalPedido.add(totalDetalle);

      DetalleResponse detalleResponse = new DetalleResponse(
          detalle.getUuid(),
          detalle.getCantidad(),
          detalle.getPlato().getNombre(),
          detalle.getPlato().getPrecio(),
          totalDetalle,
          detalle.getPlato().getUuid()
      );
      detalleResponses.add(detalleResponse);
    }

    return new PedidoResponse(
        pedido.getUuid(),
        pedido.getNumeroComprobante(),
        pedido.getClienteNombre(),
        pedido.getFechaPedido(),
        detalleResponses,
        totalPedido
    );
  }
}
