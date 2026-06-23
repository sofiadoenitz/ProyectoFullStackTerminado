package com.example.ms_juego.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_juego.Model.DTO.ModDTO;

@FeignClient(
    name = "ms-mod",
    url = "http://localhost:8087"
)
public interface ModFeignClient {
    @GetMapping("/api/v1/mod/juego/{id}")
    ModDTO obtenerMod(@PathVariable Long id);
}
