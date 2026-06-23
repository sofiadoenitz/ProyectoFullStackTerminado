package com.example.ms_descuento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.ms_descuento.Model.Descuento;
import com.example.ms_descuento.Repository.DescuentoRepository;
import com.example.ms_descuento.Service.DescuentoService;

@ActiveProfiles("test")
@SpringBootTest
public class DescuentoServiceTest {
    @Autowired
    private DescuentoService descuentoService;
    @MockBean
    private DescuentoRepository descuentoRepository;

    @Test
    public void testListarDescuento() {
        Descuento descuento = new Descuento();
        when(descuentoRepository.findAll()).thenReturn(List.of(descuento));
        List<Descuento> descuentos = descuentoService.listar();
        assertNotNull(descuentos);
        assertEquals(1, descuentos.size());
    }

    @Test
    public void testGuardarDescuento() {
        Descuento descuentoInput = new Descuento();
        when(descuentoRepository.save(descuentoInput)).thenReturn(descuentoInput);
        Descuento saved = descuentoService.agregarDescuento(descuentoInput);
        assertNotNull(saved);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Descuento descuento = new Descuento();
        when(descuentoRepository.findById(id)).thenReturn(java.util.Optional.of(descuento));
        Descuento found = descuentoService.buscar(id);
        assertNotNull(found);
    }

    @Test
    public void testActualizarDescuento() {
        Descuento descuentoInput = new Descuento();
        when(descuentoRepository.save(descuentoInput)).thenReturn(descuentoInput);
        Descuento updated = descuentoService.actualizar(descuentoInput);
        assertNotNull(updated);
    }

    @Test
    public void testEliminarDescuento() {
        Long id = 1L;
        Descuento descuento = new Descuento();
        when(descuentoRepository.findById(id)).thenReturn(java.util.Optional.of(descuento));
        descuentoService.eliminar(id);
    }

    @Test
    public void testBuscarPorJuego() {
        Long juegoId = 1L;
        Descuento descuento = new Descuento();
        when(descuentoRepository.findByJuegoId(juegoId)).thenReturn(List.of(descuento));
        List<Descuento> descuentos = descuentoService.buscarPorJuego(juegoId);
        assertNotNull(descuentos);
        assertEquals(1, descuentos.size());
    }
}
