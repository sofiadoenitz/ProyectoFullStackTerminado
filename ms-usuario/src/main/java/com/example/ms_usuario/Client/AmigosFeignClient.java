package com.example.ms_usuario.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_usuario.Model.DTO.AmigosDTO;

@FeignClient(
    name = "ms-amigos",
    url = "http://localhost:8081"
)
public interface AmigosFeignClient {
    @GetMapping("/api/v1/amigos/usuario/{id}")
    AmigosDTO obtenerAmigos(@PathVariable Long id);
}