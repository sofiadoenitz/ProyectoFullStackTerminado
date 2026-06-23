package com.example.ms_usuario.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_usuario.Model.DTO.NotificacionDTO;

@FeignClient(
    name = "ms-notificaciones",
    url = "http://localhost:8088"
)
public interface NotificacionFeignClient {
    @GetMapping("/api/v1/notificaciones/usuario/{id}")
    NotificacionDTO obtenerNotificacion(@PathVariable Long id);
}
