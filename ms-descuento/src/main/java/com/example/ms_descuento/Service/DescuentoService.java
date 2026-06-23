package com.example.ms_descuento.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_descuento.Model.Descuento;
import com.example.ms_descuento.Repository.DescuentoRepository;

@Service
public class DescuentoService {
    @Autowired
    private DescuentoRepository repo;

    public List<Descuento> listar(){
        return repo.findAll();
    }
    public Descuento buscar(Long id){
        return repo.findById(id).get();
    }
    public Descuento agregarDescuento(Descuento descuento){
        return repo.save(descuento);
    }
    public Descuento actualizar(Descuento descuento){
        return repo.save(descuento);
    }
    public String eliminar(Long id){
        repo.deleteById(id);
        return "Descuento eliminado";
    }
    public List <Descuento> buscarPorJuego(Long juegoId){
        return repo.findByJuegoId(juegoId);
    }
}