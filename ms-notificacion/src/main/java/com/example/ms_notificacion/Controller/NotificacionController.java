package com.example.ms_notificacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_notificacion.Model.Notificacion;
import com.example.ms_notificacion.Service.NotificacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService service;

    // Guardar notificación
    @PostMapping
    public Notificacion guardarNotificacion(@RequestBody Notificacion notificacion) {
        log.info("INFORMACION: Guardando nueva notificación: {}", notificacion.toString());
        return service.guardar(notificacion);
    }
    // Listar notificaciones
    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        log.info("INFORMACION: Listando todas las notificaciones");
        return service.listarNotificaciones();
    }
    // Eliminar notificación
    @DeleteMapping("/{id}")
    public String eliminarNotificacion(@PathVariable Long id) {
        log.info("INFORMACION: Eliminando notificación con id: {}", id);
        return service.eliminarNotificacion(id);
    }
    // Buscar por id
    @GetMapping("/{id}")
    public Notificacion buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando notificación con id: {}", id);
        return service.buscarPorId(id);
}
}