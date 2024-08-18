package com.curso_citior.api_restaurante.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidacionService {

  public void validarRequest(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errores = bindingResult.getFieldErrors()
          .stream()
          .map(err -> "Error en el campo '" + err.getField() + "': " + err.getDefaultMessage())
          .collect(Collectors.toList());
      throw new ProcesoException("Error en los datos enviados", errores);
    }
  }
}
