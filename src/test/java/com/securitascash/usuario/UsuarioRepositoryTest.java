package com.securitascash.usuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.securitascash.model.usuario.Usuario;
import com.securitascash.model.usuario.inherit.Administrador;
import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.repository.UsuarioRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioRepositoryTest {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Test 1:Save Administrador Test")
    @Rollback(value = false)
    void createUserAdministrador(){
        
        Usuario administrador = new Administrador();

        administrador.setNome("ets");
        administrador.setSenha("123343");
        administrador.setContas(null);
    

        usuarioRepository.save((administrador));

        //Verify
        System.out.println(administrador);
        Assertions.assertThat(administrador.getId()).isNotNull();
        

    }

    @Test
    void createUserCorrentista(){
        Usuario correntista = new Correntista();
        correntista.setNome("sou correntista");
        correntista.setSenha("876");
        
        usuarioRepository.save(correntista);
    }

}
