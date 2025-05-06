package com.securitascash.service.comentario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;

@Service
public class ComentarioService {

    public Transacao criarComentario(Transacao transacao, Comentario comentario){      
        transacao.getComentarios().add(comentario);
        return transacao;
    }

    public Transacao editarComentario(Transacao transacao, Long comentarioID, String texto){
        Comentario comentario = buscarComentario(transacao.getComentarios(), comentarioID);
        comentario.setTexto(texto);

        return transacao;
        
    }

    public void excluirComentario(){

    }

    public List<Comentario> listarComentarios(){
        return null;
    }

    public Comentario buscarComentario(List<Comentario> comentarios, Long comentarioID){
        for(Comentario comentario : comentarios){
            if (comentario.getId() == comentarioID) {return comentario;};
        }

        return null;
    }


}
