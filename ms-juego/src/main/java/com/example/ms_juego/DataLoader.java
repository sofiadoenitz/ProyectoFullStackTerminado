package com.example.ms_juego;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_juego.Model.Juego;
import com.example.ms_juego.Repository.JuegoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private JuegoRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Juego juego = new Juego();
            juego.setNombre(faker.esports().game());
            juego.setDescripcion(faker.lorem().sentence());
            juego.setCategoria(faker.options().option("Acción", "Aventura", "Deportes", "Estrategia", "Simulación"));
            juego.setDesarrolladora(faker.company().name());   
            juego.setFechaLanzamiento(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS).toString());
            juego.setDisponible(true);
            juego.setIdDescuento((long) faker.number().numberBetween(1, 100));
            juego.setIdCatalogo((long) faker.number().numberBetween(1, 100));
            juego.setIdMod((long) faker.number().numberBetween(1, 100));
            repo.save(juego);
        }
    }
    repo.flush();
    List<Juego> juegos = repo.findAll();
}
}