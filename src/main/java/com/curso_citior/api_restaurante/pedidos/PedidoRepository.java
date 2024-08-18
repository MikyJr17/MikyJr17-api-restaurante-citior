package com.curso_citior.api_restaurante.pedidos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository {

  private final IPedidoRepository iPedidoRepository;

  public PedidoRepository(IPedidoRepository iPedidoRepository) {
    this.iPedidoRepository = iPedidoRepository;
  }

  public List<Pedido> findAll() {
    return this.iPedidoRepository.findAll();
  }

  public Optional<Pedido> findById(UUID uuid) {
    return this.iPedidoRepository.findById(uuid);
  }

  public Pedido save(Pedido pedido) {
    return this.iPedidoRepository.save(pedido);
  }

  public Pedido update(Pedido pedido) {
    return this.iPedidoRepository.save(pedido);
  }

  public void deleteById(UUID uuid) {
    this.iPedidoRepository.deleteById(uuid);
  }

  public Page<Pedido> findAllPage(String clienteNombre, Pageable pageable) {
    return this.iPedidoRepository.findAllPage(clienteNombre, pageable);
  }

  public Integer maximoNumeroComprobante() {
    return iPedidoRepository.maximoNumeroComprobante();
  }
}