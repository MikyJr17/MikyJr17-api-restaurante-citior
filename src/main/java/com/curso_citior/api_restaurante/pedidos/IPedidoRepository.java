package com.curso_citior.api_restaurante.pedidos;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPedidoRepository extends JpaRepository<Pedido, UUID> {

  @Query("SELECT p FROM Pedido p WHERE p.clienteNombre LIKE %:clienteNombre% ORDER BY p.clienteNombre")
  Page<Pedido> findAllPage(@Param("clienteNombre") String clienteNombre, Pageable pageable);

  @Query("SELECT COALESCE(MAX(p.numeroComprobante), 0) FROM Pedido p")
  Integer maximoNumeroComprobante();
}