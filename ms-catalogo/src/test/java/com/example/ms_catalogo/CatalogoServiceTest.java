package com.example.ms_catalogo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import com.example.ms_catalogo.Model.Catalogo;
import com.example.ms_catalogo.Repository.CatalogoRepository;
import com.example.ms_catalogo.Service.CatalogoService;

@ActiveProfiles("test")
@SpringBootTest
public class CatalogoServiceTest {
    @Autowired
    private CatalogoService catalogoService;
    @MockBean   
    private CatalogoRepository catalogoRepository;

    @Test
    public void testListarCatalogo() {
        Catalogo catalogo = new Catalogo();
        when(catalogoRepository.findAll()).thenReturn(List.of(catalogo));
        List<Catalogo> catalogos = catalogoService.listar();
        assertNotNull(catalogos);
        assertEquals(1, catalogos.size());
    }

    @Test
    public void testGuardarCatalogo() {
        Catalogo catalogoInput = new Catalogo();
        when(catalogoRepository.save(catalogoInput)).thenReturn(catalogoInput);
        Catalogo saved = catalogoService.guardar(catalogoInput);
        assertNotNull(saved);
        verify(catalogoRepository, times(1)).save(catalogoInput);
    }

    @Test
    public void testBuscarPorId() { 
        Long id = 1L;
        Catalogo catalogo = new Catalogo();
        when(catalogoRepository.findById(id)).thenReturn(java.util.Optional.of(catalogo));
        Catalogo found = catalogoService.buscarId(id);
        assertNotNull(found);
        verify(catalogoRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarCategoria() {
        String categoria = "categoria1";
        Catalogo catalogo = new Catalogo();
        catalogo.setCategoria(categoria);
        when(catalogoRepository.findAll()).thenReturn(List.of(catalogo));
        Catalogo found = catalogoService.buscarCategoria(categoria);
        assertNotNull(found);
        assertEquals(categoria, found.getCategoria());
    }

    @Test
    public void testBuscarPlataformas() {
        String plataforma = "plataforma1";
        Catalogo catalogo = new Catalogo();
        catalogo.setPlataforma(plataforma);
        when(catalogoRepository.findAll()).thenReturn(List.of(catalogo));
        Catalogo found = catalogoService.buscarPlataformas(plataforma);
        assertNotNull(found);
        assertEquals(plataforma, found.getPlataforma());
    }
}
