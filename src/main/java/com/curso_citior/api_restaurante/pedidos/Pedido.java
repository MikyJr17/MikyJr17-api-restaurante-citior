package com.curso_citior.api_restaurante.pedidos;

import com.curso_citior.api_restaurante.detalles.Detalle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;

  private Integer numeroComprobante;

  @Column(nullable = false, length = 150)
  private String clienteNombre;

  @Column(nullable = false, updatable = false)
  private LocalDateTime fechaPedido;

  @Column(nullable = false)
  private LocalDateTime fechaActualizacion;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Detalle> detalles;

  @PrePersist
  protected void onCreate() {
    this.fechaPedido = LocalDateTime.now();
    this.fechaActualizacion = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.fechaActualizacion = LocalDateTime.now();
  }

  public Pedido(Integer numeroComprobante, String clienteNombre, LocalDateTime fechaPedido,
      List<Detalle> detalles) {
    this.numeroComprobante = numeroComprobante;
    this.clienteNombre = clienteNombre;
    this.fechaPedido = fechaPedido;
    this.detalles = detalles;
  }
}