package com.curso_citior.api_restaurante.platos;

import org.springframework.stereotype.Component;

@Component
public class PlatoMapper {

  public Plato toEntity(PlatoRequest platoRequest){
    return new Plato(
        platoRequest.getNombre(),
        platoRequest.getDescripcion(),
        platoRequest.getPrecio(),
        platoRequest.getDisponible()
    );
  }

  public PlatoResponse toResponse(Plato plato){
    return new PlatoResponse(
        plato.getUuid(),
        plato.getNombre(),
        plato.getDescripcion(),
        plato.getPrecio(),
        plato.getDisponible()
    );
  }
}
