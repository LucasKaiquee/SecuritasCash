package com.securitascash.service.correntista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Correntista> listarCorrentistas(Pageable pageable) {
        return correntistaRepository.findAll(pageable);
    }
    public Correntista criarCorrentista(Correntista correntista) {
        System.out.println("CorrentistaService.criarCorrentista() - " + correntista);
        return usuarioRepository.save(correntista);
    }

    public Page<Correntista> listarFiltrado(Boolean bloqueado, Pageable pageable) {
        if (bloqueado == null) {
            return correntistaRepository.findAll(pageable);
        } else {
            return correntistaRepository.findByBloqueado(bloqueado, pageable);
        }
    }
}

