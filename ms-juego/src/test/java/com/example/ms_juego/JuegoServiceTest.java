package com.example.ms_juego;

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

import com.example.ms_juego.Client.CatalogoFeignClient;
import com.example.ms_juego.Client.DescuentoFeignClient;
import com.example.ms_juego.Client.ModFeignClient;
import com.example.ms_juego.Model.Juego;
import com.example.ms_juego.Model.DTO.*;
import com.example.ms_juego.Repository.JuegoRepository;
import com.example.ms_juego.Service.JuegoService;

@ActiveProfiles("test")
@SpringBootTest
public class JuegoServiceTest {
    @Autowired
    private JuegoService juegoService;
    @MockBean   
    private JuegoRepository juegoRepository;
    @MockBean
    private CatalogoFeignClient catalogoClient;
    @MockBean
    private ModFeignClient modClient;
    @MockBean
    private DescuentoFeignClient descuentoClient;

    @Test
    public void testListarJuegos() {
        Juego juego = new Juego();
        when(juegoRepository.findAll()).thenReturn(List.of(juego)); 
        List<Juego> juegos = juegoService.listarJuego();
        assertEquals(1, juegos.size());
        assertEquals(juego, juegos.get(0));
    }

    @Test
    public void testGuardarJuego() {
        Juego juegoInput = new Juego();
        when(juegoRepository.save(juegoInput)).thenReturn(juegoInput);
        Juego saved = juegoService.guardarJuego(juegoInput);
        assertNotNull(saved);
        verify(juegoRepository, times(1)).save(juegoInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L; 
        Juego juego = new Juego();
        when(juegoRepository.findById(id)).thenReturn(java.util.Optional.of(juego));
        Juego found = juegoService.buscarPorId(id);
        assertNotNull(found);
        verify(juegoRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizarJuego() {
        Juego juegoInput = new Juego();
        when(juegoRepository.save(juegoInput)).thenReturn(juegoInput);
        Juego updated = juegoService.actualizarJuego(juegoInput);
        assertNotNull(updated);
        verify(juegoRepository, times(1)).save(juegoInput);
    }

    @Test
    public void testEliminarJuego() {
        Long id = 1L;
        doNothing().when(juegoRepository).deleteById(id);
        juegoService.eliminar(id);
        verify(juegoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testJuegosDisponibles() {
        Juego juego = new Juego();
        juego.setDisponible(true);
        when(juegoRepository.findByDisponible(true)).thenReturn(List.of(juego));
        List<Juego> juegosDisponibles = juegoService.juegosDisponible();
        assertEquals(1, juegosDisponibles.size());
        assertEquals(juego, juegosDisponibles.get(0));
    }

    @Test
    public void testObtenerJuegoCompleto() {
        Long id = 1L;

    Juego juego = new Juego();
    juego.setId(id);
    juego.setIdCatalogo(2L);
    juego.setIdDescuento(3L);
    juego.setIdMod(4L);

    CatalogoDTO catalogo = new CatalogoDTO();
    catalogo.setId(2L);

    DescuentoDTO descuento = new DescuentoDTO();
    descuento.setId(3L);

    ModDTO mod = new ModDTO();
    mod.setId(4L);

    when(juegoRepository.findById(id))
            .thenReturn(Optional.of(juego));

    when(catalogoClient.obtenerCatalogo(2L))
            .thenReturn(catalogo);

    when(descuentoClient.obtenerDescuento(3L))
            .thenReturn(descuento);

    when(modClient.obtenerMod(4L))
            .thenReturn(mod);

    Map<String, Object> resultado = juegoService.obtenerJuegoCompleto(id);

    assertNotNull(resultado);
    assertEquals(juego, resultado.get("juego"));
    assertEquals(catalogo, resultado.get("catalogo"));
    assertEquals(descuento, resultado.get("descuento"));
    assertEquals(mod, resultado.get("mod"));

    verify(juegoRepository, times(1)).findById(id);
    verify(catalogoClient, times(1)).obtenerCatalogo(2L);
    verify(descuentoClient, times(1)).obtenerDescuento(3L);
    verify(modClient, times(1)).obtenerMod(4L);
}


}
