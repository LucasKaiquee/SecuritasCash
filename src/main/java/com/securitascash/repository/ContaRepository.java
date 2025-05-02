package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitascash.model.conta.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    
}
