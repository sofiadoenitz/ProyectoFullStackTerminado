package com.example.ms_mod;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import com.example.ms_mod.Model.Mod;
import com.example.ms_mod.Repository.ModRepository;
import com.example.ms_mod.Service.ModService;

@ActiveProfiles("test")
@SpringBootTest
public class ModServiceTest {
    @Autowired
    private ModService modService;
    @MockBean
    private ModRepository modRepository;

    @Test
    public void testListarMods() {
        Mod mod = new Mod();
        when(modRepository.findAll()).thenReturn(List.of(mod));
        List<Mod> mods = modService.listar();
        assertNotNull(mods);
        assertEquals(1, mods.size());
    }

    @Test
    public void testGuardarMod() {
        Mod modInput = new Mod();
        when(modRepository.save(modInput)).thenReturn(modInput);
        Mod saved = modService.subir(modInput);
        assertNotNull(saved);
        verify(modRepository, times(1)).save(modInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Mod mod = new Mod();
        when(modRepository.findById(id)).thenReturn(java.util.Optional.of(mod));
        Mod found = modService.buscarPorId(id);
        assertNotNull(found);
        verify(modRepository, times(1)).findById(id);
    }

    @Test
    public void testEliminarMod() {
        Long id = 1L;
        doNothing().when(modRepository).deleteById(id);
        String result = modService.eliminar(id);
        assertEquals("Mod eliminado", result);
        verify(modRepository, times(1)).deleteById(id);
    }

    @Test
    public void testAprobarMod() {
        Long id = 1L;
        Mod mod = new Mod();
        mod.setAprobado(false);
        when(modRepository.findById(id)).thenReturn(java.util.Optional.of(mod));
        when(modRepository.save(mod)).thenReturn(mod);
        Mod approved = modService.aprobado(id);
        assertNotNull(approved);
        assertEquals(true, approved.isAprobado());
        verify(modRepository, times(1)).findById(id);
        verify(modRepository, times(1)).save(mod);
    }

    @Test
    public void testBuscarNombre() {
        String titulo = "Test Mod";
        Mod mod = new Mod();
        mod.setTitulo(titulo);
        when(modRepository.findByTitulo(titulo)).thenReturn(List.of(mod));
        List<Mod> mods = modService.buscarNombre(titulo);
        assertNotNull(mods);
        assertEquals(1, mods.size());
        assertEquals(titulo, mods.get(0).getTitulo());
    }

}
