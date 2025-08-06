package com.securitascash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securitascash.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
}
