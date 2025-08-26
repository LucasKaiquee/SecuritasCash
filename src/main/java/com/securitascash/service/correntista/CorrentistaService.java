package com.securitascash.service.correntista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securitascash.dto.correntista.CorrentistaForm;
import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.repository.CorrentistaRepository;
import com.securitascash.repository.UsuarioRepository;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Correntista> listarCorrentistas(Pageable pageable) {
        return correntistaRepository.findAll(pageable);
    }

    public void criarCorrentista(CorrentistaForm correntista) {
        Correntista novCorrentista = new Correntista();
        novCorrentista.setNome(correntista.getNome());
        novCorrentista.setEmail(correntista.getEmail());
        novCorrentista.setSenha(passwordEncoder.encode(correntista.getSenha()));
        novCorrentista.setBlocked(correntista.isBlocked());

        usuarioRepository.save(novCorrentista);
    }

    public Page<Correntista> listarFiltrado(Boolean bloqueado, Pageable pageable) {
        if (bloqueado == null) {
            return correntistaRepository.findAll(pageable);
        } else {
            return correntistaRepository.findByBloqueado(bloqueado, pageable);
        }
    }

    public Correntista buscar(Long id) {
        return correntistaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Correntista não encontrado com o ID: " + id));
    }

    public Correntista editar(Long id, Correntista dto) {
        Correntista correntista = this.buscar(id);
        correntista.setNome(dto.getNome());
        correntista.setEmail(dto.getEmail());
        correntista.setSenha(dto.getSenha());
        correntista.setBlocked(dto.isBlocked());

        return correntistaRepository.save(correntista);
    }
}
