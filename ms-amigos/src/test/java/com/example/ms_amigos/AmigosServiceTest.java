package com.example.ms_amigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.ms_amigos.Model.Amigos;
import com.example.ms_amigos.Repository.AmigosRepository;
import com.example.ms_amigos.Service.AmigosService;

@ActiveProfiles("test")
@SpringBootTest
public class AmigosServiceTest {

     @Autowired
    private AmigosService amigoservice;

    @MockBean
    private AmigosRepository amigosRepository;

   
    @Test
    public void testListar() {
        Amigos amigo = new Amigos();
        when(amigosRepository.findAll()).thenReturn(List.of(amigo));
        List<Amigos> amigos = amigoservice.listar();
        assertNotNull(amigos);
        assertEquals(1, amigos.size());
    }

    
    @Test
    public void testGuardar() {
        Amigos amigoInput = new Amigos();
        when(amigosRepository.save(amigoInput)).thenReturn(amigoInput);
        Amigos saved = amigoservice.agregar(amigoInput);
        assertNotNull(saved);
        verify(amigosRepository, times(1)).save(amigoInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Amigos amigo = new Amigos();
        when(amigosRepository.findById(id)).thenReturn(Optional.of(amigo));
        Amigos found = amigoservice.buscarPorId(id);
        assertNotNull(found);
        verify(amigosRepository, times(1)).findById(id);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;
        String result = amigoservice.eliminar(id);
        assertEquals("Amigo eliminado", result);
        verify(amigosRepository, times(1)).deleteById(id);
    }

    @Test
    public void testActualizar() {
        Amigos amigo = new Amigos();
        when(amigosRepository.save(amigo)).thenReturn(amigo);
        when(amigosRepository.findById(amigo.getId())).thenReturn(Optional.of(amigo));
        Amigos updated = amigoservice.actualizar(amigo.getId(), amigo);
        assertNotNull(updated);
        verify(amigosRepository, times(1)).save(amigo);
    }

    @Test
    public void testBuscarEstado() {
        String estado = "activo";
        Amigos amigo = new Amigos();
        amigo.setEstado(estado);
        when(amigosRepository.findByEstado(estado)).thenReturn(List.of(amigo));
        List<Amigos> amigos = amigoservice.buscarEstado(estado);
        assertNotNull(amigos);
        assertEquals(1, amigos.size());
        verify(amigosRepository, times(1)).findByEstado(estado);
    }
}
