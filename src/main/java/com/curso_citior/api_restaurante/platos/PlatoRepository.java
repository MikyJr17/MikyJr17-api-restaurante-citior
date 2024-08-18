package com.curso_citior.api_restaurante.platos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PlatoRepository {

  private final IPlatoRepository iPlatoRepository;

  public PlatoRepository(IPlatoRepository iPlatoRepository) {
    this.iPlatoRepository = iPlatoRepository;
  }

  public List<Plato> findAll() {
    return (List<Plato>) this.iPlatoRepository.findAll();
  }

  public Optional<Plato> findById(UUID uuid) {
    return this.iPlatoRepository.findById(uuid);
  }

  public Plato save(Plato plato) {
    return this.iPlatoRepository.save(plato);
  }

  public Plato update(Plato plato) {
    return this.iPlatoRepository.save(plato);
  }

  public void deleteById(UUID uuid) {
    this.iPlatoRepository.deleteById(uuid);
  }

  public Page<Plato> findAllPage(String nombre, Pageable pageable) {
    return this.iPlatoRepository.findAllPage(nombre, pageable);
  }

  public boolean existsByNombre(String nombre) {
    return this.iPlatoRepository.existsByNombre(nombre);
  }

  public boolean existsByNombreAndIdNot(String nombre, UUID uuid) {
    return this.iPlatoRepository.existsByNombreAndUuidNot(nombre, uuid);
  }
}
