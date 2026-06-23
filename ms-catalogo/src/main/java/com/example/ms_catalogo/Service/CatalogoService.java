package com.example.ms_catalogo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_catalogo.Model.Catalogo;
import com.example.ms_catalogo.Repository.CatalogoRepository;

@Service
public class CatalogoService {
    @Autowired
    private CatalogoRepository repo;

    public List <Catalogo> listar(){
        return repo.findAll();
    }

    public Catalogo buscarId(Long id){
        List<Catalogo> lista= repo.findAll();
        for(Catalogo catalogo : lista){
            if(catalogo.getId().equals(id)){
                return catalogo;
            }
        }
        return null;
    }

    public Catalogo guardar (Catalogo catalogo){
        return repo.save(catalogo);
    }

    public Catalogo buscarCategoria(String categoria){
        List <Catalogo> lista= repo.findAll();
        for(Catalogo catalogo : lista){
            if(catalogo.getCategoria().equalsIgnoreCase(categoria)){
                return catalogo;
            }
        }
        return null;
    }

    public Catalogo buscarPlataformas(String plataforma){
        List<Catalogo> lista= repo.findAll();
        for(Catalogo catalogo : lista){
            if(catalogo.getPlataforma().equalsIgnoreCase(plataforma)){
                return catalogo;
            }
        }
        return null;
    }
}