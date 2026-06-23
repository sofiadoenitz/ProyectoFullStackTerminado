package com.example.ms_descuento;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_descuento.Model.Descuento;
import com.example.ms_descuento.Repository.DescuentoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private DescuentoRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Descuento descuento = new Descuento();
            descuento.setJuegoId((long) faker.number().numberBetween(1, 100));
            descuento.setDescuento((long) faker.number().numberBetween(5, 50)); 
            descuento.setFechaInicio(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toString());
            descuento.setFechaFin(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toString());
            repo.save(descuento);
        }
    }
    repo.flush();
    List<Descuento> descuentos = repo.findAll();
}
}