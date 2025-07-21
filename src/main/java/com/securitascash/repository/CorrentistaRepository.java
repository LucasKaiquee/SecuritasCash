package com.securitascash.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.securitascash.model.usuario.inherit.Correntista;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
   
}
