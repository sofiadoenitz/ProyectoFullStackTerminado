package com.example.ms_usuario.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_usuario.Client.AmigosFeignClient;
import com.example.ms_usuario.Client.BibliotecaFeignClient;
import com.example.ms_usuario.Client.NotificacionFeignClient;
import com.example.ms_usuario.Model.DTO.AmigosDTO;
import com.example.ms_usuario.Model.DTO.BibliotecaDTO;
import com.example.ms_usuario.Model.DTO.NotificacionDTO;
import com.example.ms_usuario.Model.Usuario;
import com.example.ms_usuario.Repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private BibliotecaFeignClient bibliotecaClient;
    @Autowired
    private AmigosFeignClient amigosClient;
    @Autowired
    private NotificacionFeignClient notificacionClient;

    public Map<String, Object> obtenerDatosUsuario(Long id){
    Usuario usuario = repo.findById(id).orElse(null);
    Map<String, Object> respuesta = new HashMap<>();
    if(usuario != null){

        System.out.println(usuario.getIdBiblioteca());
        System.out.println(usuario.getIdAmigos());
        System.out.println(usuario.getIdNotificacion());
        
        BibliotecaDTO biblioteca = bibliotecaClient.obtenerBiblioteca(usuario.getIdBiblioteca());
        AmigosDTO amigos = amigosClient.obtenerAmigos(usuario.getIdAmigos());
        NotificacionDTO notificacion = notificacionClient.obtenerNotificacion(usuario.getIdNotificacion());

        respuesta.put("usuario", usuario);
        respuesta.put("biblioteca", biblioteca);
        respuesta.put("amigos", amigos);
        respuesta.put("notificacion", notificacion);
    }
    return respuesta;
    }

    @Autowired
    private UsuarioRepository repo;
    
    //Registrar Usuario
    public Usuario registrarUsuario(Usuario usu){
        return repo.save(usu);
    }

    //Listar Usuarios
    public List<Usuario> listarUsuarios(){
        return repo.findAll();
    }

    //Buscar usuario por id
    public Usuario buscarUsuario(Long id){
        return repo.findById(id).orElse(null);
    }

    //Eliminar Usuario
    public void eliminarUsuario(Long id){
        repo.deleteById(id);
    }

    //Actualizar Usuario
    public Usuario actualizarUsuario(Usuario usu){
        return repo.save(usu);
    }

}
