package com.example.ms_usuario.Model.DTO;

import lombok.Data;

@Data
public class BibliotecaDTO {
        
    private Long id;
    private Long usuarioId;
    private Long juegoId;
    private int horasJugadas;

}
