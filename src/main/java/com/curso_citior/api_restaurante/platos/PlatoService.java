package com.curso_citior.api_restaurante.platos;

import com.curso_citior.api_restaurante.exceptions.ProcesoException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlatoService {

  private final PlatoRepository platoRepository;
  private final PlatoMapper platoMapper;

  public PlatoService(PlatoRepository platoRepository, PlatoMapper platoMapper) {
    this.platoRepository = platoRepository;
    this.platoMapper = platoMapper;
  }

  public List<PlatoResponse> findAll() {
    return this.platoRepository.findAll().stream()
        .map(this.platoMapper::toResponse)
        .toList();
  }

  public Page<PlatoResponse> findAllPage(String nombre, int numeroPagina, int tamanoPagina) {
    Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);
    return this.platoRepository.findAllPage(nombre, pageable)
        .map(this.platoMapper::toResponse);
  }

  public PlatoResponse findById(UUID uuid) {
    return this.platoRepository.findById(uuid)
        .map(this.platoMapper::toResponse)
        .orElseThrow(() -> new ProcesoException("Plato no encontrado"));
  }

  @Transactional
  public PlatoResponse save(PlatoRequest platoRequest) {
    if (this.platoRepository.existsByNombre(platoRequest.getNombre())) {
      throw new ProcesoException("El nombre del plato ya está registrado");
    }
    Plato plato = this.platoMapper.toEntity(platoRequest);
    Plato platoSaved = this.platoRepository.save(plato);
    return this.platoMapper.toResponse(platoSaved);
  }

  @Transactional
  public PlatoResponse update(UUID uuid, PlatoRequest platoRequest) {
    Plato platoExisting = this.platoRepository.findById(uuid)
        .orElseThrow(() -> new ProcesoException("Plato no encontrado"));

    if (this.platoRepository.existsByNombreAndIdNot(platoRequest.getNombre(), uuid)) {
      throw new ProcesoException("El nombre del plato ya está registrado");
    }

    platoExisting.setNombre(platoRequest.getNombre());
    platoExisting.setDescripcion(platoRequest.getDescripcion());
    platoExisting.setPrecio(platoRequest.getPrecio());
    platoExisting.setDisponible(platoRequest.getDisponible());

    Plato platoUpdated = this.platoRepository.update(platoExisting);
    return this.platoMapper.toResponse(platoUpdated);
  }

  @Transactional
  public void deleteById(UUID uuid) {
    if (!this.platoRepository.findById(uuid).isPresent()) {
      throw new ProcesoException("Plato no encontrado");
    }
    this.platoRepository.deleteById(uuid);
  }
}
