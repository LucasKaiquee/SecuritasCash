package com.securitascash.usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.securitascash.model.usuario.Administrador;
import com.securitascash.model.usuario.Correntista;
import com.securitascash.model.usuario.Usuario;
import com.securitascash.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioTest {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void createUserAdministrador(){
        
        Usuario administrador = new Administrador();

        administrador.setNome("ets");
        administrador.setSenha("123343");
        administrador.setContas(null);
    

        usuarioRepository.save((administrador));
        

    }

    @Test
    void createUserCorrentista(){
        Usuario correntista = new Correntista();
        correntista.setNome("sou correntista");
        correntista.setSenha("876");
        
        usuarioRepository.save(correntista);
    }
}
