package com.securitascash.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public String cadastrarUsuario(){
        //TODO
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

    public String bloquearUsuario(){
        //TODO
        return null;
    }


}
