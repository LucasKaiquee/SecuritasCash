package com.securitascash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securitascash.model.conta.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findContaByUsuario_id(Long id);
    
}
