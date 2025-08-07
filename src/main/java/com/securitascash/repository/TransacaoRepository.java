package com.securitascash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.securitascash.enums.Movimento;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
    Page<Transacao> findAllByConta(Conta conta, Pageable pageable);

    @Query("SELECT t FROM Transacao t WHERE " +
            "(t.conta = :conta) AND " +
           "(:movimento IS NULL OR t.movimento = :movimento)")
    Page<Transacao> findByFilters(
            @Param("conta") Conta conta,
            @Param("movimento") Movimento movimento,
            Pageable pageable // O Pageable receberá a ordenação correta AUTOMATICAMENTE
    );
}
