package com.securitascash.comentario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;
import com.securitascash.repository.TransacaoRepository;
import com.securitascash.service.comentario.ComentarioService;

@SpringBootTest
public class ComentarioServiceTest {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ComentarioService comentarioService;

    @Test
    void criaComentarioNaTransacao(){

        Transacao transacao = new Transacao();
        Comentario comentario = new Comentario();

        comentario.setTexto("Texto");

        transacao = comentarioService.criarComentario(transacao, comentario);

        Assertions.assertThat(transacao.getComentarios().size()).isGreaterThan(0);
    }


    @Test
    void editaTextoDoComentario(){

        Transacao transacao = new Transacao();
        Comentario comentario = new Comentario();

        comentario.setId(1L);
        comentario.setTexto("Texto");

        transacao.getComentarios().add(comentario);

        transacao = comentarioService.editarComentario(transacao, 1L, "Novo Texto");

        assertEquals(transacao.getComentarios().size(), 1);
        assertEquals(transacao.getComentarios().get(0).getTexto(), "Novo Texto");
    }


    @Test
    void excluiUmComentario(){

        Transacao transacao = new Transacao();
        
        Comentario c1 = new Comentario();
        Comentario c2 = new Comentario();
        Comentario c3 = new Comentario();

        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);

        transacao.getComentarios().add(c1);
        transacao.getComentarios().add(c2);
        transacao.getComentarios().add(c3);

        assertEquals(transacao.getComentarios().size(), 3);

        transacao = comentarioService.excluirComentario(transacao, 2L);

        assertEquals(transacao.getComentarios().size(), 2);
        Assertions.assertThat(c2).isNotIn(transacao.getComentarios());
    }


    @Test
    void buscaComentarioPorIdEComparaOTexto(){
        Transacao transacao = new Transacao();

        Comentario c1 = new Comentario();
        Comentario c2 = new Comentario();
        Comentario c3 = new Comentario();

        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);

        c2.setTexto("Texto");

        transacao.getComentarios().add(c1);
        transacao.getComentarios().add(c2);
        transacao.getComentarios().add(c3);

        Comentario comentarioBuscado = comentarioService.buscarComentario(transacao.getComentarios(), 2L);

        assertEquals(comentarioBuscado.getTexto(), "Texto");

    }

    @Test
    void listaTresComentarios(){

        Transacao transacao = new Transacao();

        Comentario c1 = new Comentario();
        Comentario c2 = new Comentario();
        Comentario c3 = new Comentario();

        transacao.getComentarios().add(c1);
        transacao.getComentarios().add(c2);
        transacao.getComentarios().add(c3);

        List<Comentario> comentarios = comentarioService.listarComentarios(transacao);

        assertEquals(comentarios.size(), 3);

    }
}
