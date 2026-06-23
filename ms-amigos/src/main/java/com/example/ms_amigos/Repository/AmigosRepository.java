package com.example.ms_amigos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_amigos.Model.Amigos;

@Repository
public interface AmigosRepository extends JpaRepository <Amigos, Long>{
    List<Amigos> findByEstado(String estado);

}
