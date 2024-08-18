package com.curso_citior.api_restaurante.platos;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPlatoRepository extends JpaRepository<Plato, UUID> {

  @Query("SELECT p FROM Plato p WHERE p.nombre LIKE %:nombre% ORDER BY p.nombre")
  Page<Plato> findAllPage(@Param("nombre") String nombre, Pageable pageable);

  boolean existsByNombre(String nombre);

  boolean existsByNombreAndUuidNot(String nombre, UUID uuid);
}