package com.example.ms_factorizacion.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_factorizacion.Client.PagoFeignClient;
import com.example.ms_factorizacion.Model.DTO.PagoDTO;
import com.example.ms_factorizacion.Model.Factura;
import com.example.ms_factorizacion.Repository.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository repo;
    @Autowired
    private PagoFeignClient pagoClient;

    //Listar
    public List<Factura> listarFactura(){
        return repo.findAll();
    }

    //Guardar
    public Factura guardarFactura(Factura fac){
        return repo.save(fac);
    }

    //Actualizar
    public Factura actualizarFactura(Factura fac){
        return repo.save(fac);
    }
    //Eliminar
    public void eliminar(Long id){
        repo.deleteById(id);
    }

    //Buscar por id
    public Factura buscarPorId(Long id){
        return repo.findById(id).orElse(null);
    }

    //Buscar por metodo pago
    public List<Factura> metodoPago(String metodo){
        return repo.findByMetodoPago(metodo);
    }
    //Conexion
    public Map<String, Object>obtenerFacturaCompleta(Long id){

        Factura factura = repo.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if(factura != null){

            PagoDTO pago = pagoClient.obtenerPago(factura.getPagoId());
            respuesta.put("factura", factura);
            respuesta.put("pago", pago);
        }

        return respuesta;
    }
}
