package com.securitascash.service.correntista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.repository.CorrentistaRepository;
import com.securitascash.repository.UsuarioRepository;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Correntista> listarCorrentistas() {
        return correntistaRepository.findAll();
    }
    public Correntista criarCorrentista(Correntista correntista) {
        System.out.println("CorrentistaService.criarCorrentista() - " + correntista);
        return usuarioRepository.save(correntista);
    }
}

