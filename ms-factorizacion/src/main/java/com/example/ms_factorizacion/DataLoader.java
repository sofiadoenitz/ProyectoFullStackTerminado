package com.example.ms_factorizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_factorizacion.Model.Factura;
import com.example.ms_factorizacion.Repository.FacturaRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private FacturaRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Factura factura = new Factura();
            factura.setPagoId((long) faker.number().numberBetween(1, 100));
            factura.setFechaFactura(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toString());
            factura.setMetodoPago(faker.options().option("Efectivo", "Tarjeta de crédito", "Transferencia bancaria"));
            factura.setTotal(faker.number().numberBetween(100, 1000));
            repo.save(factura);
        }
    }
    repo.flush();
    List<Factura> facturas = repo.findAll();
}
}