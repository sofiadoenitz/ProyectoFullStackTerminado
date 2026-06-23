package com.example.ms_juego.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_juego.Model.Juego;
import com.example.ms_juego.Service.JuegoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/juego")
public class JuegoController {

    @Autowired
    private JuegoService serv;

    // LISTAR
    @GetMapping
    public List<Juego> listar(){
        log.info("INFORMACION: Listando todos los juegos");
        return serv.listarJuego();
    }

    // GUARDAR
    @PostMapping
    public Juego guardar(@RequestBody Juego juego){
        log.info("INFORMACION: Guardando nuevo juego: {}", juego.toString());
        return serv.guardarJuego(juego);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public Juego actualizar(@PathVariable Long id, @RequestBody Juego jue){
        log.info("INFORMACION: Actualizando juego con id: {}", id);
        return serv.actualizarJuego(jue);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Juego buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando juego con id: {}", id);
        return serv.buscarPorId(id);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando juego con id: {}", id);
        serv.eliminar(id);
    }

    // DISPONIBLES
    @GetMapping("/disponibles")
    public List<Juego> disponibles(){
        log.info("INFORMACION: Listando juegos disponibles");
        return serv.juegosDisponible();
    }

    // CONEXIONES
    @GetMapping("/completo/{id}")
    public Map<String, Object> completo(
            @PathVariable Long id){
        log.info("INFORMACION: Obteniendo datos completos del juego con id: {}", id);
        return serv.obtenerJuegoCompleto(id);
    }
}

