package com.example.ms_catalogo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_catalogo.Model.Catalogo;
import com.example.ms_catalogo.Repository.CatalogoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private CatalogoRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Catalogo catalogo = new Catalogo();
            catalogo.setCategoria(faker.esports().game());
            catalogo.setPlataforma(faker.esports().league());
            catalogo.setClasificacion(faker.esports().event());
            catalogo.setJuegoId((long) faker.number().numberBetween(1, 100));
            repo.save(catalogo);
        }
    }
    repo.flush();
    List<Catalogo> catalogos = repo.findAll();
}
}