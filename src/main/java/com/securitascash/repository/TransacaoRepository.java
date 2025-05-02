package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitascash.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
}
