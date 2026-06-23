package com.example.ms_pago.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long usuarioId;
    private Long juegoId;
    private Long descuentoId;

    private int total;
    private String metodoPago;
    private String fechaPago;

}
