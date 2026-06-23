package com.example.ms_biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_biblioteca.Model.Biblioteca;


public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long>{
    List<Biblioteca> findByInstalado(boolean instalado);

}
