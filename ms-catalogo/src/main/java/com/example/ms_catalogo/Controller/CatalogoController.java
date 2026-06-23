package com.example.ms_catalogo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_catalogo.Model.Catalogo;
import com.example.ms_catalogo.Service.CatalogoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService serv;

    //Listar
    @GetMapping
    public List <Catalogo> listar(){
        log.info("INFORMACION: Listando todos los catálogos");
        return serv.listar();
    }
    //Buscar por id
    @GetMapping("/{id}")
    public Catalogo buscarId(@PathVariable Long id){
        log.info("INFORMACION: Buscando catálogo con id: {}", id);
        return serv.buscarId(id);
    }
    //Guardar
    @PostMapping
    public Catalogo guardar(@RequestBody Catalogo catalogo){
        log.info("INFORMACION: Guardando nuevo catálogo: {}", catalogo.toString());
        return serv.guardar(catalogo);
    }
    //Buscar por categoria
    @GetMapping ("/categoria/{categoria}")
    public Catalogo buscarCategoria(@PathVariable String categoria){
        log.info("INFORMACION: Buscando catálogo por categoría: {}", categoria);
        return serv.buscarCategoria(categoria);
    } 
    //Buscar por plataforma
    @GetMapping("/plataforma/{plataforma}")
    public Catalogo buscarPlataformas(@PathVariable String plataforma){
        log.info("INFORMACION: Buscando catálogo por plataforma: {}", plataforma);
        return serv.buscarPlataformas(plataforma);
    }
}