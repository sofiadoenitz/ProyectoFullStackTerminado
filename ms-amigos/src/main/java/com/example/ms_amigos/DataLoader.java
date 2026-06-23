package com.example.ms_amigos;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_amigos.Model.Amigos;
import com.example.ms_amigos.Repository.AmigosRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private AmigosRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Amigos amigo = new Amigos();
            amigo.setIdUsuario((long) faker.number().numberBetween(1, 100));
            amigo.setEstado(faker.options().option("pendiente", "aceptado", "rechazado"));
            repo.save(amigo);
        }
    }
    repo.flush();
    List<Amigos> amigos = repo.findAll();
}
}