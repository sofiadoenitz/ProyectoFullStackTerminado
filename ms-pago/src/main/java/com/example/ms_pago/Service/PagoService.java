package com.example.ms_pago.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_pago.Client.DescuentoFeignClient;
import com.example.ms_pago.Client.JuegoFeignClient;
import com.example.ms_pago.Client.UsuarioFeignClient;
import com.example.ms_pago.Model.Pago;
import com.example.ms_pago.Model.DTO.DescuentoDTO;
import com.example.ms_pago.Model.DTO.JuegoDTO;
import com.example.ms_pago.Model.DTO.UsuarioDTO;
import com.example.ms_pago.Repository.PagoRepository;

@Service
public class PagoService {
    @Autowired
    private PagoRepository repo;
    @Autowired
    private UsuarioFeignClient usuarioClient;
    @Autowired
    private JuegoFeignClient juegoClient;
    @Autowired
    private DescuentoFeignClient descuentoClient;
    
    //Listar
    public List<Pago> listarPago(){
        return repo.findAll();
    }
    //Guardar
    public Pago guardarPago(Pago pago){
        return repo.save(pago);
    }
    //Actualizar
    public Pago actualizarPago(Pago pago){
        return repo.save(pago);
    }
    //Metodo Pago
    public List<Pago> metodoPago(String metodo){
        return repo.findByMetodoPago(metodo);
    }

    //Buscar por id
    public Pago buscarPorId(Long id){
        return repo.findById(id).orElse(null);
    }
    
    //Conexiones
    public Map<String, Object> obtenerPagoCompleto(Long id){

        Pago pago = repo.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();
        if(pago != null){

            UsuarioDTO usuario = usuarioClient.obtenerUsuario(pago.getUsuarioId());

            JuegoDTO juego = juegoClient.obtenerJuego(pago.getJuegoId());

            DescuentoDTO descuento = descuentoClient.obtenerDescuento(pago.getDescuentoId());

            respuesta.put("pago", pago);
            respuesta.put("usuario", usuario);
            respuesta.put("juego", juego);
            respuesta.put("descuento", descuento);
        }

        return respuesta;
    }
    


}
