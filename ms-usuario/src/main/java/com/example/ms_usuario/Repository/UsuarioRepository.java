package com.example.ms_usuario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_usuario.Model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

}
