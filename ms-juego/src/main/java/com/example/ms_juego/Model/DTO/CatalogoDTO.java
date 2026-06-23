package com.example.ms_juego.Model.DTO;

import lombok.Data;

@Data
public class CatalogoDTO {
    private Long id;
    private String categoria;
    private String plataforma;
    private boolean disponible;

}
