package com.securitascash.service.transacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.Transacao;
import com.securitascash.repository.TransacaoRepository;
import com.securitascash.service.comentario.ComentarioService;

@Service
public class TransacaoService {
    
    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ComentarioService comentarioService;


    public void criarTransacao(){
        //TODO
    }

    public void editarTransacao(){
        //TODO
    }

    public List<Transacao> listarTransacoes(){
        //TODO
        return null;
    }

    public void adicionarComentario(){
        //TODO
    }

    public void editarComentario(){
        //TODO
    }

    public void excluirComentario(){
        //TODO
    }


}
