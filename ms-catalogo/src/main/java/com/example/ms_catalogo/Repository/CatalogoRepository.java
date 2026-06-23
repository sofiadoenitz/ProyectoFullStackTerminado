package com.example.ms_catalogo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_catalogo.Model.Catalogo;
@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>{
}