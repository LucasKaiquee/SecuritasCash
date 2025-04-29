package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitascash.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
