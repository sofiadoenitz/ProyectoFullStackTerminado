package com.example.ms_juego.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_juego.Model.DTO.CatalogoDTO;

@FeignClient(
    name = "ms-catalogo",
    url = "http://localhost:8083"
)
public interface CatalogoFeignClient {
    @GetMapping("/api/v1/catalogo/juego/{id}")
    CatalogoDTO obtenerCatalogo(@PathVariable Long id);
}
