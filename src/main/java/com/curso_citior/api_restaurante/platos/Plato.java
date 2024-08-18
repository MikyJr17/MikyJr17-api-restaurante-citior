package com.curso_citior.api_restaurante.platos;

import com.curso_citior.api_restaurante.detalles.Detalle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.math.BigDecimal;
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
public class Plato {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;

  @Column(nullable = false, unique = true, length = 100)
  private String nombre;

  @Column(columnDefinition = "TEXT")
  private String descripcion;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal precio;

  @Column(nullable = false)
  private Boolean disponible = true;

  @Column(nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

  @Column(nullable = false)
  private LocalDateTime fechaActualizacion;

  @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Detalle> detalles;

  @PrePersist
  protected void onCreate() {
    this.fechaCreacion = LocalDateTime.now();
    this.fechaActualizacion = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.fechaActualizacion = LocalDateTime.now();
  }

  public Plato(String nombre, String descripcion, BigDecimal precio, Boolean disponible) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.disponible = disponible;
  }
}