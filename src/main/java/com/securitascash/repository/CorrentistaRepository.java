package com.securitascash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securitascash.model.usuario.inherit.Correntista;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
    
    @Query("SELECT c FROM Correntista c WHERE c.isBlocked = ?1")
    Page<Correntista> findByBloqueado(Boolean bloqueado, Pageable pageable);
}
