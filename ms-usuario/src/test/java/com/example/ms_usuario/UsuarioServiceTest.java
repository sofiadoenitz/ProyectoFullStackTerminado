package com.example.ms_usuario;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import com.example.ms_usuario.Client.AmigosFeignClient;
import com.example.ms_usuario.Client.BibliotecaFeignClient;
import com.example.ms_usuario.Client.NotificacionFeignClient;
import com.example.ms_usuario.Model.Usuario;
import com.example.ms_usuario.Model.DTO.AmigosDTO;
import com.example.ms_usuario.Model.DTO.BibliotecaDTO;
import com.example.ms_usuario.Model.DTO.NotificacionDTO;
import com.example.ms_usuario.Repository.UsuarioRepository;
import com.example.ms_usuario.Service.UsuarioService;

@ActiveProfiles("test")
@SpringBootTest
public class UsuarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private AmigosFeignClient amigosFeignClient;
    @MockBean
    private BibliotecaFeignClient bibliotecaFeignClient;
    @MockBean
    private NotificacionFeignClient notificacionFeignClient;

    @Test
    public void testListarUsuarios() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testGuardarUsuario() {
        Usuario usuarioInput = new Usuario();
        when(usuarioRepository.save(usuarioInput)).thenReturn(usuarioInput);
        Usuario saved = usuarioService.registrarUsuario(usuarioInput);
        assertNotNull(saved);
        verify(usuarioRepository, times(1)).save(usuarioInput);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        Usuario found = usuarioService.buscarUsuario(id);
        assertNotNull(found);
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    public void testEliminarUsuario() {
    Long id = 1L;

    doNothing().when(usuarioRepository).deleteById(id);
    usuarioService.eliminarUsuario(id);

    verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuarioInput = new Usuario();
        when(usuarioRepository.save(usuarioInput)).thenReturn(usuarioInput);
        Usuario updated = usuarioService.actualizarUsuario(usuarioInput);
        assertNotNull(updated);
        verify(usuarioRepository, times(1)).save(usuarioInput);
    }
    
    @Test
    public void testobtenerUsuarioCompleto() {
    Long id = 1L;
    Usuario usuario = new Usuario();

    AmigosDTO amigosMock = new AmigosDTO(); 
    BibliotecaDTO bibliotecaMock = new BibliotecaDTO();
    NotificacionDTO notificacionMock = new NotificacionDTO();

    when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
    when(amigosFeignClient.obtenerAmigos(id)).thenReturn(amigosMock);
    when(bibliotecaFeignClient.obtenerBiblioteca(id)).thenReturn(bibliotecaMock);
    when(notificacionFeignClient.obtenerNotificacion(id)).thenReturn(notificacionMock);

    Map<String, Object> resultado = usuarioService.obtenerDatosUsuario(id);

    assertNotNull(resultado);
    assertEquals(usuario, resultado.get("usuario"));
}

}
