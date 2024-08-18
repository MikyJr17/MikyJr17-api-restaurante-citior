package com.curso_citior.api_restaurante.detalles;

import com.curso_citior.api_restaurante.pedidos.Pedido;
import com.curso_citior.api_restaurante.platos.Plato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "pedido_detalle")
public class Detalle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;

  @Column(nullable = false)
  private Integer cantidad;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "pedido_uuid")
  private Pedido pedido;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "plato_uuid", referencedColumnName = "uuid")
  private Plato plato;
}
