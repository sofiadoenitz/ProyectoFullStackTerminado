package com.example.ms_pago.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_pago.Model.DTO.DescuentoDTO;

@FeignClient(
    name = "ms-descuento",
    url = "http://localhost:8084"
)
public interface DescuentoFeignClient {
    @GetMapping("/api/v1/descuento/{id}")
    DescuentoDTO obtenerDescuento(@PathVariable Long id);
}
