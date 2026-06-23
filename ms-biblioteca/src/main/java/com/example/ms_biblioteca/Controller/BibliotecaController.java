package com.example.ms_biblioteca.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_biblioteca.Model.Biblioteca;
import com.example.ms_biblioteca.Service.BibliotecaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/biblioteca")
public class BibliotecaController {
    @Autowired
    private BibliotecaService serv;
    
    //Listar
    @GetMapping
    public List<Biblioteca> listar(){
        log.info("INFORMACION: Listando todas las bibliotecas");
        return serv.listarBiblioteca();
    }

    //Guardar
    @PostMapping
    public Biblioteca guardar(@RequestBody Biblioteca biblio){
        log.info("INFORMACION: Guardando nueva biblioteca: {}", biblio.toString());
        return serv.guardar(biblio);
    }

    //Actualizar
    @PutMapping("/{id}")
    public Biblioteca actualizar(@PathVariable Long id, @RequestBody Biblioteca biblio){
        log.info("INFORMACION: Actualizando biblioteca con id: {}", id);
        return serv.actualizarBiblioteca(biblio);
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Biblioteca buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando biblioteca con id: {}", id);
        return serv.buscarPorId(id);
    }

    //Conexiones
    @GetMapping("/completa/{id}")
    public Map<String, Object> completa(@PathVariable Long id){
        log.info("INFORMACION: Obteniendo datos completos de la biblioteca con id: {}", id);
        return serv.obtenerBibliotecaCompleta(id);
    }

}
