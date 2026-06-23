package com.example.ms_biblioteca.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "biblioteca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Biblioteca {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long juegoId;

    private int horasJugadas;
    private boolean instalado;
}