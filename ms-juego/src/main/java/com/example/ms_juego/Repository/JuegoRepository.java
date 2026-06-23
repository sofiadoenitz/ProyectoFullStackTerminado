package com.example.ms_juego.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_juego.Model.Juego;

public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByDisponible(boolean disponible);
    List<Juego> findByCategoria(String categoria);
    List<Juego> findByNombreContaining(String nombre);

}
