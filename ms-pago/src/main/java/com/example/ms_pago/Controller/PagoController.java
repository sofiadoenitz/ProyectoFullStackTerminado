package com.example.ms_pago.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_pago.Model.Pago;
import com.example.ms_pago.Service.PagoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {
    @Autowired
    private PagoService serv;
    
    //Listar
    @PostMapping
    public List<Pago> listar(){
        log.info("INFORMACION: Listando todos los pagos");
        return serv.listarPago();
    }
    //Guardar
    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable Long id, @RequestBody Pago pago){
        log.info("INFORMACION: Actualizando pago con id: {}", id);
        return serv.actualizarPago(pago);
    }
    //Buscar por metodo
     @GetMapping("/metodo/{metodo}")
    public List<Pago> metodo(@PathVariable String metodo){
        log.info("INFORMACION: Buscando pagos por método: {}", metodo);
        return serv.metodoPago(metodo);
    }
    //Buscar por id
    @GetMapping("/{id}")
    public Pago buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando pago con id: {}", id);
        return serv.buscarPorId(id);
    }
    
    //Completo
     @GetMapping("/completo/{id}")
    public Map<String, Object> completo(@PathVariable Long id){
        log.info("INFORMACION: Obteniendo datos completos del pago con id: {}", id);
        return serv.obtenerPagoCompleto(id);
    }


}
