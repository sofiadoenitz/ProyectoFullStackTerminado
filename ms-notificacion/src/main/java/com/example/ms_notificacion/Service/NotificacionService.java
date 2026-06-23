package com.example.ms_notificacion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_notificacion.Model.Notificacion;
import com.example.ms_notificacion.Repository.NotificacionRepository;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository repo;

    public Notificacion guardar(Notificacion notificacion) {
        return repo.save(notificacion);
    }

    public List<Notificacion> listarNotificaciones() {
        return repo.findAll();
    }

    public String eliminarNotificacion(Long id) {
        repo.deleteById(id);
        return "Notificacion eliminada";
    }
    
    public Notificacion buscarPorId(Long id){
        return repo.findById(id).orElse(null);
}

}
