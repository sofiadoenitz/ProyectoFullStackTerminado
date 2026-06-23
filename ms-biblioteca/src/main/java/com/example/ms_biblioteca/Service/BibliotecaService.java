package com.example.ms_biblioteca.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_biblioteca.Client.JuegoFeignClient;
import com.example.ms_biblioteca.Model.DTO.JuegoDTO;
import com.example.ms_biblioteca.Model.Biblioteca;
import com.example.ms_biblioteca.Repository.BibliotecaRepository;

@Service
public class BibliotecaService {
    @Autowired
    private BibliotecaRepository repo;

    @Autowired
    private JuegoFeignClient juegoClient;

    //Listar
    public List<Biblioteca> listarBiblioteca(){
        return repo.findAll();
    }

    //Guardar
    public Biblioteca guardar(Biblioteca biblio){
        return repo.save(biblio);
    }

    //Actualizar
    public Biblioteca actualizarBiblioteca(Biblioteca biblio){
        return repo.save(biblio);
    }

    //Conexiones
     public Map<String, Object>
            obtenerBibliotecaCompleta(Long id){

        Biblioteca biblioteca = repo.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();
        if(biblioteca != null){

            JuegoDTO juego = juegoClient.obtenerJuego(biblioteca.getJuegoId());
            respuesta.put("biblioteca", biblioteca);
            respuesta.put("juego", juego);
        }
        return respuesta;
    }
    //Buscar id
    public Biblioteca buscarPorId(Long id){
        return repo.findById(id).orElse(null);
}
}
