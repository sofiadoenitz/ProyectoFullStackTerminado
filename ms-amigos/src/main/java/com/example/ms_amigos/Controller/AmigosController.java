package com.example.ms_amigos.Controller;

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

import com.example.ms_amigos.Model.Amigos;
import com.example.ms_amigos.Service.AmigosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/amigos")
public class AmigosController {

    @Autowired
    private AmigosService serv;

    //Listar
    @GetMapping
    public List<Amigos>listar(){
        log.info("INFORMACION: Listando todos los amigos");
        return serv.listar();
    }
    //Guardar
    @PostMapping
    public Amigos guardar(@RequestBody Amigos amigos){
        log.info("INFORMACION: Guardando nuevo amigo: {}", amigos.toString());
        return serv.agregar(amigos);
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando amigo con id: {}", id);
        return serv.eliminar(id);
    }

    //Buscar por estado
    @GetMapping("/estado/{estado}")
    public List <Amigos>buscarEstado(@PathVariable String estado){
        log.info("INFORMACION: Buscando amigos por estado: {}", estado);
        return serv.buscarEstado(estado);
    }

    //Actualizar
    @PutMapping("/{id}")
    public Amigos actualizar(@PathVariable Long id, @RequestBody Amigos amigo) {
        log.info("INFORMACION: Actualizando amigo con id: {}", id);
        return serv.actualizar(id, amigo);
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Amigos buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando amigo con id: {}", id);
        return serv.buscarPorId(id);
    }
}
