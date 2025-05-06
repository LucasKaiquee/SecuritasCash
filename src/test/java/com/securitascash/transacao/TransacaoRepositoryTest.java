package com.securitascash.transacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;
import com.securitascash.repository.TransacaoRepository;
import com.securitascash.service.comentario.ComentarioService;


@SpringBootTest
public class TransacaoRepositoryTest {
    
    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ComentarioService comentarioService;

    @Test
    void createComentario(){
        Transacao t = new Transacao();
        Comentario c = new Comentario();

        t.setComentarios(new ArrayList<>());
        c.setTexto("Texto");

        t = comentarioService.criarComentario(t, c);

        assertEquals(t.getComentarios().getFirst().getTexto(), "Texto");
    }

    @Test
    void editaComentario(){

        Transacao t = new Transacao();
        Comentario c = new Comentario();

        t.setComentarios(new ArrayList<>());
        c.setId(1l);
        c.setTexto("Texto");

        t.getComentarios().add(c);

        t = comentarioService.editarComentario(t, 1L, "Novo Texto");

        assertEquals(t.getComentarios().size(), 1);
        assertEquals(t.getComentarios().get(0).getTexto(), "Novo Texto");
    }

    @Test
    void buscarComentario(){

    }
    
}
