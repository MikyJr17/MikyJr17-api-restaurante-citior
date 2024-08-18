package com.curso_citior.api_restaurante.platos;

import com.curso_citior.api_restaurante.exceptions.ValidacionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platos")
public class PlatoController {

  private final PlatoService platoService;
  private final ValidacionService validacionService;

  public PlatoController(PlatoService platoService, ValidacionService validacionService) {
    this.platoService = platoService;
    this.validacionService = validacionService;
  }

  @GetMapping
  public ResponseEntity<List<PlatoResponse>> findAll() {
    List<PlatoResponse> platos = this.platoService.findAll();
    if (platos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(platos);
  }

  @GetMapping("/page")
  public ResponseEntity<Page<PlatoResponse>> findAllPage(
      @RequestParam(required = false) String nombre,
      @RequestParam(defaultValue = "0") int numeroPagina,
      @RequestParam(defaultValue = "5") int tamanoPagina) {
    Page<PlatoResponse> platoPage = this.platoService.findAllPage(nombre, numeroPagina,
        tamanoPagina);
    if (platoPage.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(platoPage);
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<PlatoResponse> findById(@PathVariable("uuid") UUID uuid) {
    PlatoResponse plato = this.platoService.findById(uuid);
    return ResponseEntity.ok(plato);
  }

  @PostMapping
  public ResponseEntity<PlatoResponse> save(
      @Valid @RequestBody PlatoRequest platoRequest,
      BindingResult bindingResult) {
    this.validacionService.validarRequest(bindingResult);
    PlatoResponse platoSaved = this.platoService.save(platoRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(platoSaved);
  }

  @PutMapping("/{uuid}")
  public ResponseEntity<PlatoResponse> update(
      @PathVariable("uuid") UUID uuid,
      @Valid @RequestBody PlatoRequest platoRequest,
      BindingResult bindingResult) {
    this.validacionService.validarRequest(bindingResult);
    PlatoResponse platoUpdated = this.platoService.update(uuid, platoRequest);
    return ResponseEntity.ok(platoUpdated);
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid) {
    this.platoService.deleteById(uuid);
    return ResponseEntity.noContent().build();
  }
}