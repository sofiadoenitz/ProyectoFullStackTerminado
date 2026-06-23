package com.example.ms_pago;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import com.example.ms_pago.Client.DescuentoFeignClient;
import com.example.ms_pago.Client.JuegoFeignClient;
import com.example.ms_pago.Client.UsuarioFeignClient;
import com.example.ms_pago.Model.Pago;
import com.example.ms_pago.Model.DTO.DescuentoDTO;
import com.example.ms_pago.Model.DTO.JuegoDTO;
import com.example.ms_pago.Model.DTO.UsuarioDTO;
import com.example.ms_pago.Repository.PagoRepository;
import com.example.ms_pago.Service.PagoService;

@ActiveProfiles("test")
@SpringBootTest
public class PagoServiceTest {
    @Autowired
    private PagoService pagoService;
    @MockBean
    private PagoRepository pagoRepository;
    @MockBean
    private DescuentoFeignClient descuentoFeignClient;
    @MockBean
    private JuegoFeignClient juegoFeignClient;
    @MockBean
    private UsuarioFeignClient usuarioFeignClient;

    @Test
    public void testListarPagos() {
        Pago pago = new Pago();
        when(pagoRepository.findAll()).thenReturn(List.of(pago));
        List<Pago> pagos = pagoService.listarPago();
        assertNotNull(pagos);
        assertEquals(1, pagos.size());
    }

    @Test
    public void testGuardarPago() {
        Pago pagoInput = new Pago();
        when(pagoRepository.save(pagoInput)).thenReturn(pagoInput);
        Pago saved = pagoService.guardarPago(pagoInput);
        assertNotNull(saved);
        verify(pagoRepository, times(1)).save(pagoInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Pago pago = new Pago();
        when(pagoRepository.findById(id)).thenReturn(java.util.Optional.of(pago));
        Pago found = pagoService.buscarPorId(id);
        assertNotNull(found);
        verify(pagoRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizarPago() {
        Pago pagoInput = new Pago();
        when(pagoRepository.save(pagoInput)).thenReturn(pagoInput);
        Pago updated = pagoService.actualizarPago(pagoInput);
        assertNotNull(updated);
        verify(pagoRepository, times(1)).save(pagoInput);
    }

    @Test
    public void testMetodoPago() {
    String tipoMetodo = "Tarjeta"; 
    Pago pago = new Pago();
    List<Pago> listaSimulada = new ArrayList<>();
    listaSimulada.add(pago);

    when(pagoRepository.findByMetodoPago(tipoMetodo)).thenReturn(listaSimulada);

    List<Pago> resultado = pagoService.metodoPago(tipoMetodo);
    
    assertNotNull(resultado);
    assertFalse(resultado.isEmpty()); 
    verify(pagoRepository, times(1)).findByMetodoPago(tipoMetodo);
    }

    @Test
    public void obtenerPagoCompleto() {
    Long id = 1L;

    Pago pago = new Pago();
    pago.setDescuentoId(2L);
    pago.setJuegoId(3L);
    pago.setUsuarioId(4L);

    DescuentoDTO descuentoMock = new DescuentoDTO(); 
    JuegoDTO juegoMock = new JuegoDTO();
    UsuarioDTO usuarioMock = new UsuarioDTO();

    when(pagoRepository.findById(id)).thenReturn(java.util.Optional.of(pago));
    when(descuentoFeignClient.obtenerDescuento(2L)).thenReturn(descuentoMock);
    when(juegoFeignClient.obtenerJuego(3L)).thenReturn(juegoMock);
    when(usuarioFeignClient.obtenerUsuario(4L)).thenReturn(usuarioMock);

    var resultado = pagoService.obtenerPagoCompleto(id);

    assertNotNull(resultado);
    assertEquals(pago, resultado.get("pago"));
    }
}