package com.example.ms_biblioteca;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.ms_biblioteca.Client.JuegoFeignClient;
import com.example.ms_biblioteca.Model.Biblioteca;
import com.example.ms_biblioteca.Model.DTO.JuegoDTO;
import com.example.ms_biblioteca.Repository.BibliotecaRepository;
import com.example.ms_biblioteca.Service.BibliotecaService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class BibliotecaServiceTest {
    @Autowired
    private BibliotecaService bibliotecaService;
    @MockBean
    private BibliotecaRepository bibliotecaRepository;
    @MockBean
    private JuegoFeignClient juegoFeignClient;

    @Test
    public void testListarBiblioteca() {
        Biblioteca biblioteca = new Biblioteca();
        when(bibliotecaRepository.findAll()).thenReturn(List.of(biblioteca));
        List<Biblioteca> bibliotecas = bibliotecaService.listarBiblioteca();
        assertNotNull(bibliotecas);
        assertEquals(1, bibliotecas.size());
    }   

    @Test
    public void testGuardarBiblioteca() {
        Biblioteca bibliotecaInput = new Biblioteca();
        when(bibliotecaRepository.save(bibliotecaInput  )).thenReturn(bibliotecaInput);
        Biblioteca saved = bibliotecaService.guardar(bibliotecaInput);
        assertNotNull(saved);
        verify(bibliotecaRepository, times(1)).save(bibliotecaInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Biblioteca biblioteca = new Biblioteca();
        when(bibliotecaRepository.findById(id)).thenReturn(java.util.Optional.of(biblioteca));
        Biblioteca found = bibliotecaService.buscarPorId(id);
        assertNotNull(found);
        verify(bibliotecaRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizarBiblioteca() {
        Biblioteca bibliotecaInput = new Biblioteca();
        when(bibliotecaRepository.save(bibliotecaInput)).thenReturn(bibliotecaInput);
        Biblioteca updated = bibliotecaService.actualizarBiblioteca(bibliotecaInput);
        assertNotNull(updated);
        verify(bibliotecaRepository, times(1)).save(bibliotecaInput);
    }

    @Test
    public void testObtenerBibliotecaCompleta() {
        Long id = 1L;

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setJuegoId(2L);
        JuegoDTO juego = new JuegoDTO();
        juego.setId(2L);
        when(bibliotecaRepository.findById(id)).thenReturn(Optional.of(biblioteca));
        when(juegoFeignClient.obtenerJuego(2L)).thenReturn(juego);
        Map<String, Object> resultado =  bibliotecaService.obtenerBibliotecaCompleta(id);
        assertNotNull(resultado);
        assertEquals(biblioteca, resultado.get("biblioteca"));
        assertEquals(juego, resultado.get("juego"));
    }

}
