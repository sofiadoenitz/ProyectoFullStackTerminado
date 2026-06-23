package com.example.ms_mod.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_mod.Model.Mod;
import com.example.ms_mod.Repository.ModRepository;

@Service
public class ModService {

    @Autowired
    private ModRepository repo;

    public List<Mod> listar() {
        return repo.findAll();
    }

    public Mod subir(Mod mod) {
        return repo.save(mod);
    }

    public String eliminar(Long id) {
        repo.deleteById(id);
        return "Mod eliminado";
    }

    public Mod aprobado(Long id) {
        Mod mod = repo.findById(id).get();
        mod.setAprobado(true);
        return repo.save(mod);
    }

    public List<Mod> buscarNombre(String titulo) {
        return repo.findByTitulo(titulo);
    }

    public Mod buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

}
