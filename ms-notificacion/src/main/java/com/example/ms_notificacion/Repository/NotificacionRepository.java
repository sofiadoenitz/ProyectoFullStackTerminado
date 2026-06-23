package com.example.ms_notificacion.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_notificacion.Model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository <Notificacion, Long>{
    List<Notificacion> findByUsuarioId(Long usuarioId);
    List<Notificacion> findByleido(boolean leido);
}
