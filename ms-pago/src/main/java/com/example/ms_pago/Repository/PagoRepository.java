package com.example.ms_pago.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_pago.Model.Pago;

public interface PagoRepository extends JpaRepository<Pago,Long> {
    List<Pago> findByMetodoPago(String metodoPago);
    List<Pago> findByUsuarioId(Long usuarioId);

}
