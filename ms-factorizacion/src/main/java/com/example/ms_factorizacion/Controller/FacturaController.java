package com.example.ms_factorizacion.Controller;

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

import com.example.ms_factorizacion.Model.Factura;
import com.example.ms_factorizacion.Service.FacturaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/factura")
public class FacturaController {
    @Autowired
    private FacturaService serv;

    // Listar
    @GetMapping
    public List<Factura> listar(){
        log.info("INFORMACION: Listando todas las facturas");
        return serv.listarFactura();
    }

    //Guardar
    @PostMapping
    public Factura guardar(@RequestBody Factura fac){
        log.info("INFORMACION: Guardando nueva factura: {}", fac.toString());
        return serv.guardarFactura(fac);
    }

    //Buscar por id
    @GetMapping("/{id}")    
    public Factura buscar(@PathVariable Long id){
        log.info("INFORMACION: Buscando factura con id: {}", id);
        return serv.buscarPorId(id);
    }

    //Eliminar
    @GetMapping("/eliminar/{id}")   
    public String eliminar(@PathVariable Long id){
        log.info("INFORMACION: Eliminando factura con id: {}", id);
        serv.eliminar(id);
        return "Factura eliminada";
    }

    //Actualizar
    @PutMapping("/{id}")
    public Factura actualizar(@PathVariable Long id, @RequestBody Factura fac){
        log.info("INFORMACION: Actualizando factura con id: {}", id);
        return serv.actualizarFactura(fac);
    }

    //Metodo pago
    @GetMapping("/metodo/{metodo}")
    public List<Factura> metodo(@PathVariable String metodo){
        log.info("INFORMACION: Buscando facturas por método de pago: {}", metodo);
        return serv.metodoPago(metodo);
    }

    //Conexion
    @GetMapping("/completa/{id}")
    public Map<String, Object> completa(@PathVariable Long id){
        log.info("INFORMACION: Obteniendo datos completos de la factura con id: {}", id);
        return serv.obtenerFacturaCompleta(id);
    }

}
