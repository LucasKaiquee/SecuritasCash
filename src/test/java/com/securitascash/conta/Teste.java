package com.securitascash.conta;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.securitascash.model.conta.Conta;
import com.securitascash.model.conta.inherit.CartaoDeCredito;
import com.securitascash.model.conta.inherit.ContaCorrente;
import com.securitascash.repository.ContaRepository;
import com.securitascash.repository.UsuarioRepository;

@DataJpaTest
public class Teste {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;
    

    @Test
    void createContaCorrente(){
        
        Conta c = new ContaCorrente();
        c.setUsuario(usuarioRepository.findById(1L).orElse(null));
        
        c.setNumero("123");
        c.setTransacoes(null);
        this.contaRepository.save(c);
    }

    @Test
    void createCartaoDeCredito(){
        Conta c = new CartaoDeCredito();
        ((CartaoDeCredito) c).setDiaDoFechamento(10);
        
        this.contaRepository.save(c);
        

        
    }

    @Test
    void listaTodosTiposDeConta(){
        List<Conta> lista = contaRepository.findAll();

        assertNotEquals(0, lista.size());
        
    }

    
}
