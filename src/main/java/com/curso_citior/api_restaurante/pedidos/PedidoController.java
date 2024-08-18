package com.curso_citior.api_restaurante.pedidos;

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
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService pedidoService;
  private final ValidacionService validacionService;

  public PedidoController(PedidoService pedidoService, ValidacionService validacionService) {
    this.pedidoService = pedidoService;
    this.validacionService = validacionService;
  }

  @GetMapping
  public ResponseEntity<List<PedidoResponse>> findAll() {
    List<PedidoResponse> pedidos = this.pedidoService.findAll();
    if (pedidos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(pedidos);
  }

  @GetMapping("/page")
  public ResponseEntity<Page<PedidoResponse>> findAllPage(
      @RequestParam(required = false) String clienteNombre,
      @RequestParam(defaultValue = "0") int numeroPagina,
      @RequestParam(defaultValue = "5") int tamanoPagina) {
    Page<PedidoResponse> pedidoPage = this.pedidoService.findAllPage(clienteNombre, numeroPagina,
        tamanoPagina);
    if (pedidoPage.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(pedidoPage);
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<PedidoResponse> findById(@PathVariable("uuid") UUID uuid) {
    PedidoResponse pedido = this.pedidoService.findById(uuid);
    return ResponseEntity.ok(pedido);
  }

  @PostMapping
  public ResponseEntity<PedidoResponse> save(@Valid @RequestBody PedidoRequest pedidoRequest,
      BindingResult bindingResult) {
    this.validacionService.validarRequest(bindingResult);
    PedidoResponse pedidoSaved = this.pedidoService.save(pedidoRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSaved);
  }

  @PutMapping("/{uuid}")
  public ResponseEntity<PedidoResponse> update(@PathVariable("uuid") UUID uuid,
      @Valid @RequestBody PedidoRequest pedidoRequest, BindingResult bindingResult) {
    this.validacionService.validarRequest(bindingResult);
    PedidoResponse pedidoUpdated = this.pedidoService.update(uuid, pedidoRequest);
    return ResponseEntity.ok(pedidoUpdated);
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid) {
    this.pedidoService.deleteById(uuid);
    return ResponseEntity.noContent().build();
  }
}