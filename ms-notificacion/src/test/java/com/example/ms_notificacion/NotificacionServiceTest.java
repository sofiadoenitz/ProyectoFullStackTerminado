package com.example.ms_notificacion;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import com.example.ms_notificacion.Model.Notificacion;
import com.example.ms_notificacion.Repository.NotificacionRepository;
import com.example.ms_notificacion.Service.NotificacionService;

@ActiveProfiles("test")
@SpringBootTest
public class NotificacionServiceTest {
    @Autowired
    private NotificacionService notificacionService;
    @MockBean
    private NotificacionRepository notificacionRepository;

    @Test
    public void testListarNotificaciones() {
        Notificacion notificacion = new Notificacion();
        when(notificacionRepository.findAll()).thenReturn(List.of(notificacion));
        List<Notificacion> notificaciones = notificacionService.listarNotificaciones();
        assertNotNull(notificaciones);
        assertEquals(1, notificaciones.size());
    }

    @Test
    public void testGuardarNotificacion() {
        Notificacion notificacionInput = new Notificacion();
        when(notificacionRepository.save(notificacionInput)).thenReturn(notificacionInput);
        Notificacion saved = notificacionService.guardar(notificacionInput);
        assertNotNull(saved);
        verify(notificacionRepository, times(1)).save(notificacionInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Notificacion notificacion = new Notificacion();
        when(notificacionRepository.findById(id)).thenReturn(java.util.Optional.of(notificacion));
        Notificacion found = notificacionService.buscarPorId(id);
        assertNotNull(found);
        verify(notificacionRepository, times(1)).findById(id);
    }

    @Test
    public void testEliminarNotificacion() {
        Long id = 1L;
        doNothing().when(notificacionRepository).deleteById(id);
        String result = notificacionService.eliminarNotificacion(id);
        assertEquals("Notificacion eliminada", result);
        verify(notificacionRepository, times(1)).deleteById(id);
    }

}
