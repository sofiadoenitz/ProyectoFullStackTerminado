package com.example.ms_factorizacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.example.ms_factorizacion.Client.PagoFeignClient;
import com.example.ms_factorizacion.Model.Factura;
import com.example.ms_factorizacion.Model.DTO.PagoDTO;
import com.example.ms_factorizacion.Repository.FacturaRepository;
import com.example.ms_factorizacion.Service.FacturaService;

@ActiveProfiles("test")
@SpringBootTest
public class FacturaServiceTest {
    @Autowired
    private FacturaService facturaService;
    @MockBean
    private FacturaRepository facturaRepository;
    @MockBean
    private PagoFeignClient pagoFeignClient;

    @Test
    public void testListarFacturas() {
        Factura factura = new Factura();
        when(facturaRepository.findAll()).thenReturn(List.of(factura)); 
        List<Factura> facturas = facturaService.listarFactura();
        assertEquals(1, facturas.size());
        assertEquals(factura, facturas.get(0));
    }

    @Test
    public void testGuardarFactura() {
        Factura facturaInput = new Factura();
        when(facturaRepository.save(facturaInput)).thenReturn(facturaInput);
        Factura saved = facturaService.guardarFactura(facturaInput);
        assertNotNull(saved);
        verify(facturaRepository, times(1)).save(facturaInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L; 
        Factura factura = new Factura();
        when(facturaRepository.findById(id)).thenReturn(java.util.Optional.of(factura));
        Factura found = facturaService.buscarPorId(id);
        assertNotNull(found);
        verify(facturaRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizarFactura() {
        Factura facturaInput = new Factura();
        when(facturaRepository.save(facturaInput)).thenReturn(facturaInput);
        Factura updated = facturaService.actualizarFactura(facturaInput);
        assertNotNull(updated);
        verify(facturaRepository, times(1)).save(facturaInput);
    }

    @Test
    public void testEliminarFactura() {
        Long id = 1L;
        doNothing().when(facturaRepository).deleteById(id);
        facturaService.eliminar(id);
        verify(facturaRepository, times(1)).deleteById(id);
    }

    @Test
        public void testObtenerFacturaCompleta() {
        Long id = 1L;

        Factura factura = new Factura();
        factura.setPagoId(2L);
        PagoDTO pago = new PagoDTO();
        pago.setId(2L);
        when(facturaRepository.findById(id)).thenReturn(Optional.of(factura));
        when(pagoFeignClient.obtenerPago(2L)).thenReturn(pago);
        Map<String, Object> resultado =  facturaService.obtenerFacturaCompleta(id);
        assertNotNull(resultado);
        assertEquals(factura, resultado.get("factura"));
        assertEquals(pago, resultado.get("pago"));
    }
}
