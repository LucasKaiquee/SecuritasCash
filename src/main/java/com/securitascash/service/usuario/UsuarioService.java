package com.securitascash.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.usuario.Usuario;
import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public String cadastrarUsuario(){
        //TODO: Perguntar ao professor se usuários Administradores poderão ser criados ou serão objetos mockados.
        return null;
    }

    public String editarUsuario(){
        //TODO
        return null;
    }

    public String deletarUsuario(){
        //TODO
        return null;
    }

    public Usuario buscarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    public Usuario bloquearUsuarioCorrentista(Long userID){
        Usuario usuarioCorrentista = usuarioRepository.findById(userID).orElse(null);
        ((Correntista)usuarioCorrentista).setBlocked(true);
        
        return usuarioCorrentista;
    }


}
