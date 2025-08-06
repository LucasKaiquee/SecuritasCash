package com.securitascash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securitascash.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmailAndSenha(String email, String senha);
    
    Usuario findByEmail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE TYPE(u) = Correntista")
    List<Usuario> findAllCorrentistas();
}
