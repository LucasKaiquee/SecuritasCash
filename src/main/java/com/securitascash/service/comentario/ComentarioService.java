package com.securitascash.service.comentario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.securitascash.exception.BussinessException;
import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;

@Service
public class ComentarioService {

    // private void verificarTextoNaoEVazio (String texto) throws BussinessException{
    //     if (texto == null || texto.isEmpty()){
    //         throw new BussinessException("Texto do comentário não pode ser vazio.");
    //     }
    // }

    public Comentario criarComentario(Transacao transacao, Comentario comentario){  
        // this.verificarTextoNaoEVazio(comentario.getTexto());
        
        transacao.getComentarios().add(comentario);
        comentario.setTransacao(transacao);

        return comentario;
    }

    public Comentario editarComentario(Transacao transacao, Long comentarioID, String texto){
        Comentario comentario = buscarComentario(transacao.getComentarios(), comentarioID);

        // this.verificarTextoNaoEVazio(texto);
        comentario.setTexto(texto);
        
        return comentario;
        
    }

    public Transacao excluirComentario(Transacao transacao, Long comentarioID){
        Comentario comentarioASerExcluido = buscarComentario(transacao.getComentarios(), comentarioID);
        transacao.getComentarios().remove(comentarioASerExcluido);

        return transacao;
    }

    public List<Comentario> listarComentarios(Transacao transacao ){
        return transacao.getComentarios();
    }

    
    public Comentario buscarComentario(List<Comentario> comentarios, Long comentarioID) throws RuntimeException{
        for(Comentario comentario : comentarios){
            if (comentario.getId() == comentarioID) {return comentario;};
        }

        throw new RuntimeException("Comentario não encontrado com o id: " + comentarioID);

    }


}
