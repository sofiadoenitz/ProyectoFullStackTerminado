package com.example.ms_descuento.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_descuento.Model.Descuento;
import com.example.ms_descuento.Service.DescuentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/descuentos")
public class DescuentoController {
    @Autowired
    private DescuentoService serv;

    //Listar
    @GetMapping
    public List <Descuento>listar(){
        log.info("INFORMACION: Listando todos los descuentos");
        return serv.listar();
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Descuento buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando descuento con id: {}", id);
        return serv.buscar(id);
    }

    //Guardar
    @PostMapping
    public Descuento agregargarDescuento (@RequestBody Descuento descuento){
        log.info("INFORMACION: Guardando nuevo descuento: {}", descuento.toString());
        return serv.agregarDescuento(descuento);
    }

    //Actualizar
    @PutMapping("/{id}")
    public Descuento actualizar(@PathVariable Long id, @RequestBody Descuento descuento){
        log.info("INFORMACION: Actualizando descuento con id: {}", id);
        return serv.actualizar(descuento);
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando descuento con id: {}", id);
        return serv.eliminar(id);
    }
    @GetMapping("/juego/{juego}")
    public List <Descuento> buscarPorJuego(@PathVariable Long juegoId){
        log.info("INFORMACION: Buscando descuentos para el juego con id: {}", juegoId);
        return serv.buscarPorJuego(juegoId);
    }
}