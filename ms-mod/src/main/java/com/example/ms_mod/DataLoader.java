package com.example.ms_mod;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ms_mod.Model.Mod;
import com.example.ms_mod.Repository.ModRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private ModRepository repo;

    @Override
    public void run(String... args) throws Exception {
    if (repo.count() == 0) {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Mod mod = new Mod();
            mod.setTitulo(faker.esports().game());
            mod.setDescripcion(faker.lorem().sentence());
            mod.setCreador(faker.name().fullName());
            mod.setVersion(faker.app().version());
            mod.setAprobado(false);
            repo.save(mod);
        }
    }
    repo.flush();
    List<Mod> mods = repo.findAll();
}
}