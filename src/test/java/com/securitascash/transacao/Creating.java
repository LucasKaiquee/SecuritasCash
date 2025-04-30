package com.securitascash.transacao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.securitascash.enums.Movimento;
import com.securitascash.model.Transacao;
import com.securitascash.repository.CategoriaRepository;
import com.securitascash.repository.ContaRepository;
import com.securitascash.repository.TransacaoRepository;

@SpringBootTest
public class Creating {
    
    @Autowired
    TransacaoRepository transacaoRepository;
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    ContaRepository contaRepository;

    @Test
    void createTransacao(){
        Transacao transacao = new Transacao();

        transacao.setCategoria(categoriaRepository.findById(2L).orElse(null));
        transacao.setComentario("Comentario sobre a transacao.");
        transacao.setConta(contaRepository.findById(1L).orElse(null));
        transacao.setData(new Date());
        transacao.setDescricao("Descricao bem importante.");
        transacao.setMovimento(Movimento.D);
        transacao.setValor(new BigDecimal(10000));

        this.transacaoRepository.save(transacao);
    }

    @Test
    void listTransacao(){
        
        List<Transacao> transacoes = transacaoRepository.findAll();

        assertNotEquals(0, transacoes.size());
    }
    
}
