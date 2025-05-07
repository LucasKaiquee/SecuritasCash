package com.securitascash.service.transacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.Comentario;
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

    
    //TODO: Ver qual a melhor forma de adicionar os comentários no momento de criação da transação
    //      Opção 1 : Usar DTO
        
    public void adicionarComentario(Comentario comentario){
    }

    public void editarComentario(){
    }

    public void excluirComentario(){
    }


}
