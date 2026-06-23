package com.example.ms_biblioteca;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_biblioteca.Model.Biblioteca;
import com.example.ms_biblioteca.Repository.BibliotecaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private BibliotecaRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.setHorasJugadas(faker.number().numberBetween(1, 100));
            biblioteca.setInstalado(faker.bool().bool());
            biblioteca.setJuegoId((long) faker.number().numberBetween(1, 100));
            repo.save(biblioteca);
        }
    }
    repo.flush();
    List<Biblioteca> bibliotecas = repo.findAll();
}
}