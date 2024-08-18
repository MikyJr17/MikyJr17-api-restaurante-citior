package com.curso_citior.api_restaurante.pedidos;

import com.curso_citior.api_restaurante.detalles.Detalle;
import com.curso_citior.api_restaurante.detalles.DetalleRequest;
import com.curso_citior.api_restaurante.exceptions.ProcesoException;
import com.curso_citior.api_restaurante.platos.Plato;
import com.curso_citior.api_restaurante.platos.PlatoRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final PedidoMapper pedidoMapper;
  private final PlatoRepository platoRepository;

  public PedidoService(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper,
      PlatoRepository platoRepository) {
    this.pedidoRepository = pedidoRepository;
    this.pedidoMapper = pedidoMapper;
    this.platoRepository = platoRepository;
  }

  public List<PedidoResponse> findAll() {
    return this.pedidoRepository.findAll()
        .stream()
        .map(this.pedidoMapper::mapToResponse)
        .toList();
  }

  public Page<PedidoResponse> findAllPage(String clienteNombre, int numeroPagina,
      int tamanoPagina) {
    Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);
    return this.pedidoRepository.findAllPage(clienteNombre, pageable)
        .map(this.pedidoMapper::mapToResponse);
  }

  public PedidoResponse findById(UUID uuid) {
    Pedido pedido = this.pedidoRepository.findById(uuid)
        .orElseThrow(() -> new ProcesoException("Pedido no encontrado"));
    return this.pedidoMapper.mapToResponse(pedido);
  }

  @Transactional
  public synchronized PedidoResponse save(PedidoRequest pedidoRequest) {
    Integer maxNumeroComprobante = this.pedidoRepository.maximoNumeroComprobante();
    Integer numeroComprobante = maxNumeroComprobante == null ? 1 : maxNumeroComprobante + 1;

    List<Detalle> detalles = new ArrayList<>();
    for (DetalleRequest detalleRequest : pedidoRequest.getDetalles()) {
      Plato plato = this.platoRepository.findById(detalleRequest.getPlatoUuid())
          .orElseThrow(() -> new ProcesoException("Plato no encontrado"));
      Detalle detalle = new Detalle();
      detalle.setCantidad(detalleRequest.getCantidad());
      detalle.setPlato(plato);
      detalles.add(detalle);
    }

    Pedido pedido = new Pedido(numeroComprobante, pedidoRequest.getClienteNombre(), null, detalles);
    for (Detalle detalle : detalles) {
      detalle.setPedido(pedido);
    }

    Pedido pedidoGuardado = this.pedidoRepository.save(pedido);
    return this.pedidoMapper.mapToResponse(pedidoGuardado);
  }

  @Transactional
  public PedidoResponse update(UUID uuid, PedidoRequest pedidoRequest) {
    Pedido pedido = this.pedidoRepository.findById(uuid)
        .orElseThrow(() -> new ProcesoException("Pedido no encontrado"));
    pedido.setClienteNombre(pedidoRequest.getClienteNombre());

    List<Detalle> detalles = new ArrayList<>();
    for (DetalleRequest detalleRequest : pedidoRequest.getDetalles()) {
      Plato plato = this.platoRepository.findById(detalleRequest.getPlatoUuid())
          .orElseThrow(() -> new ProcesoException("Plato no encontrado"));
      Detalle detalle = new Detalle();
      detalle.setCantidad(detalleRequest.getCantidad());
      detalle.setPedido(pedido);
      detalle.setPlato(plato);
      detalles.add(detalle);
    }
    pedido.getDetalles().clear();
    pedido.getDetalles().addAll(detalles);

    Pedido updatedPedido = this.pedidoRepository.update(pedido);
    return this.pedidoMapper.mapToResponse(updatedPedido);
  }

  @Transactional
  public void deleteById(UUID uuid) {
    if (!this.pedidoRepository.findById(uuid).isPresent()) {
      throw new ProcesoException("Pedido no encontrado");
    }
    this.pedidoRepository.deleteById(uuid);
  }
}