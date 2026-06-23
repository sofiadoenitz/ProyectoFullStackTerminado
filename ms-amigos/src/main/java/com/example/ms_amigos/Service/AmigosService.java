package com.example.ms_amigos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_amigos.Model.Amigos;
import com.example.ms_amigos.Repository.AmigosRepository;

@Service
public class AmigosService {

    @Autowired
    private AmigosRepository repo;

    public List <Amigos> listar(){
        return repo.findAll();
    }
    public Amigos agregar(Amigos amigos){
        return repo.save(amigos);
    }
    public String eliminar(Long id){
        repo.deleteById(id);
        return "Amigo eliminado";
    }
    public List <Amigos> buscarEstado(String estado){
        return repo.findByEstado(estado);
    }
    public Amigos actualizar(Long id, Amigos amigoActualizado) {
        Amigos amigos = repo.findById(id).get();
        amigos.setEstado(amigoActualizado.getEstado());
        return repo.save(amigos);
    }
    public Amigos buscarPorId(Long id){
        return repo.findById(id).orElse(null);
}

}
