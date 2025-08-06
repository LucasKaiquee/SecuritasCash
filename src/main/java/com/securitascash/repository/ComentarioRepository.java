package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securitascash.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}
