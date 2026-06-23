package com.example.ms_factorizacion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_factorizacion.Model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
    List<Factura> findByMetodoPago(String metodoPago);
}
