package com.securitascash.repository;

import java.time.LocalDate;
import java.util.List;

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

    List<Transacao> findByContaIdAndDataBetweenOrderByDataAsc(Long contaId, LocalDate inicio, LocalDate fim);
    Page<Transacao> findByContaIdAndDataBetween(Long contaId, LocalDate inicio, LocalDate fim, Pageable pageable);
    Page<Transacao> findByContaIdAndMovimentoAndDataBetween(Long contaId, Movimento movimento, LocalDate inicio, LocalDate fim, Pageable pageable);
    List<Transacao> findByContaIdAndDataBetween(Long contaId, LocalDate inicio, LocalDate fim);

    @Query("SELECT t FROM Transacao t WHERE " +
            "(t.conta = :conta) AND " +
           "(:movimento IS NULL OR t.movimento = :movimento)")
    Page<Transacao> findByFilters(
            @Param("conta") Conta conta,
            @Param("movimento") Movimento movimento,
            Pageable pageable // O Pageable receberá a ordenação correta AUTOMATICAMENTE
    );
}
