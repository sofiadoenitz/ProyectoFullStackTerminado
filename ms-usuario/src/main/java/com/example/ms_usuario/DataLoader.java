package com.example.ms_usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_usuario.Model.Usuario;
import com.example.ms_usuario.Repository.UsuarioRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private UsuarioRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Usuario usuario = new Usuario();
            usuario.setUsuario(faker.name().username());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setIdAmigos((long) faker.number().numberBetween(1, 100));
            usuario.setIdBiblioteca((long) faker.number().numberBetween(1, 100));
            usuario.setIdNotificacion((long) faker.number().numberBetween(1, 100));
            repo.save(usuario);
        }
    }
    repo.flush();
    List<Usuario> usuarios = repo.findAll();
}
}