package com.example.ms_pago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_pago.Model.Pago;
import com.example.ms_pago.Repository.PagoRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private PagoRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Pago pago = new Pago();
            pago.setUsuarioId((long) faker.number().numberBetween(1, 100));
            pago.setJuegoId((long) faker.number().numberBetween(1, 100));
            pago.setDescuentoId((long) faker.number().numberBetween(1, 100));
            pago.setFechaPago(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toString());
            pago.setMetodoPago(faker.options().option("Efectivo", "Tarjeta de crédito", "Transferencia bancaria"));
            pago.setTotal(faker.number().numberBetween(100, 1000));
            repo.save(pago);
        }
    }
    repo.flush();
    List<Pago> pagos = repo.findAll();
}
}