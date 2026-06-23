package com.example.ms_factorizacion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_factorizacion.Model.DTO.PagoDTO;

@FeignClient(
    name = "ms-pago",
    url = "http://localhost:8086"
)
public interface PagoFeignClient {
    @GetMapping("/api/v1/pago/{id}")
    PagoDTO obtenerPago(@PathVariable Long id);
}
