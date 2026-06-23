package com.example.ms_mod.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_mod.Model.Mod;

@Repository
public interface ModRepository extends JpaRepository <Mod, Long> {
    List <Mod> findByAprobado(boolean aproobado);

    List <Mod> findByTitulo(String titulo);

}
