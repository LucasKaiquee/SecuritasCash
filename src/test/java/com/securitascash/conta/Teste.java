package com.securitascash.conta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.securitascash.model.conta.CartaoDeCredito;
import com.securitascash.model.conta.Conta;
import com.securitascash.model.conta.ContaCorrente;
import com.securitascash.repository.ContaRepository;

@SpringBootTest
public class Teste {

    @Autowired
    ContaRepository contaRepository;
    

    @Test
    void createContaCorrente(){
        
        Conta c = new ContaCorrente();
        
        c.setNumero("123");
        c.setTransacoes(null);
        c.setUsuario(null);
        this.contaRepository.save(c);
    }

    @Test
    void createCartaoDeCredito(){
        Conta c = new CartaoDeCredito();

        ((CartaoDeCredito) c).setDiaDoFechamento(10);
        

        contaRepository.save(c);
        

        
    }

    @Test
    void listaTodosTiposDeConta(){
        List<Conta> lista = contaRepository.findAll();

        assertEquals(2, lista.size());
    }
}
