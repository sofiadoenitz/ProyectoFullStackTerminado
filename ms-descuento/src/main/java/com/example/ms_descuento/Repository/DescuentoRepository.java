package com.example.ms_descuento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_descuento.Model.Descuento;

public interface DescuentoRepository extends JpaRepository <Descuento, Long>{
    List<Descuento> findByJuegoId(Long juegoId);
    List<Descuento> findByDescuento(Long descuento);
}