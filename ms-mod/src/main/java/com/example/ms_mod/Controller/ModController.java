package com.example.ms_mod.Controller;

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

import com.example.ms_mod.Model.Mod;
import com.example.ms_mod.Service.ModService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/mods")
public class ModController {

    @Autowired
    private ModService serv;

    // LISTAR
    @GetMapping
    public List<Mod> listar() {
        log.info("INFORMACION: Listando todos los mods");
        return serv.listar();
    }
    // SUBIR MOD
    @PostMapping
    public Mod subir(@RequestBody Mod mod) {
        log.info("INFORMACION: Subiendo nuevo mod: {}", mod.toString());
        return serv.subir(mod);
    }
    // ELIMINAR
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        log.info("INFORMACION: Eliminando mod con id: {}", id);
        return serv.eliminar(id);
    }
    // APROBAR
    @PutMapping("/aprobado/{aprobado}")
    public Mod aprobado(@PathVariable Long id) {
        log.info("INFORMACION: Actualizando estado del mod con id: {}", id);
        return serv.aprobado(id);
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Mod buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando mod con id: {}", id);
        return serv.buscarPorId(id);
    }
    //Buscar por nombre
    @GetMapping("/titulo/{titulo}")
    public List<Mod> buscarNombre(@PathVariable String titulo) {
        log.info("INFORMACION: Buscando mods por título: {}", titulo);
        return serv.buscarNombre(titulo);
    }

}
