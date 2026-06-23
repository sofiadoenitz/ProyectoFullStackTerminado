package com.example.ms_usuario.Controller;

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

import com.example.ms_usuario.Model.Usuario;
import com.example.ms_usuario.Service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService serv;

    //Listar
    @GetMapping
    public List<Usuario> listar(){
        log.info("INFORMACION: Listando todos los usuarios");
        return serv.listarUsuarios();
    }

    //Guardar
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usu){
        log.info("INFORMACION: Guardando nuevo usuario: {}", usu.toString());
        return serv.registrarUsuario(usu);
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando usuario con id: {}", id);
        serv.eliminarUsuario(id);
    }

    //Actualizar
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usu){
        log.info("INFORMACION: Actualizando usuario con id: {}", id);
        return serv.actualizarUsuario(usu);
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando usuario con id: {}", id);
        return serv.buscarUsuario(id);
    }

    //Conexiones
    @GetMapping("/completo/{id}")
    public Map<String, Object> completo(@PathVariable Long id){
        log.info("INFORMACION: Obteniendo datos completos del usuario con id: {}", id);
        return serv.obtenerDatosUsuario(id);
    }

}
