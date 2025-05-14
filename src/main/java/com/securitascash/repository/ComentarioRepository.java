package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitascash.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}
