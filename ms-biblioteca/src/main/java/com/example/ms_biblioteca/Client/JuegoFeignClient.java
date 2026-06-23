package com.example.ms_biblioteca.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_biblioteca.Model.DTO.JuegoDTO;

@FeignClient(
    name = "ms-juego",
    url = "http://localhost:8086"
)
public interface JuegoFeignClient {
    @GetMapping("/api/v1/juego/{id}")
    JuegoDTO obtenerJuego(@PathVariable Long id);
}