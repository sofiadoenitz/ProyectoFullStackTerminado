package com.example.ms_usuario.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_usuario.Model.DTO.BibliotecaDTO;

@FeignClient(
    name = "ms-biblioteca",
    url = "http://localhost:8082"
)
public interface BibliotecaFeignClient {
    @GetMapping("/api/v1/biblioteca/usuario/{id}")
    BibliotecaDTO obtenerBiblioteca(@PathVariable Long id);

}
