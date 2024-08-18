package com.curso_citior.api_restaurante.detalles;

import com.curso_citior.api_restaurante.pedidos.Pedido;
import com.curso_citior.api_restaurante.platos.Plato;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class DetalleMapper {

  public Detalle toEntity(DetalleRequest request, Plato plato, Pedido pedido) {
    Detalle detalle = new Detalle();
    detalle.setCantidad(request.getCantidad());
    detalle.setPlato(plato);
    detalle.setPedido(pedido);
    return detalle;
  }

  public DetalleResponse toResponse(Detalle detalle) {
    BigDecimal precio = detalle.getPlato().getPrecio();
    BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(detalle.getCantidad()));
    return new DetalleResponse(
        detalle.getUuid(),
        detalle.getCantidad(),
        detalle.getPlato().getNombre(),
        precio,
        subtotal,
        detalle.getPlato().getUuid()
    );
  }
}