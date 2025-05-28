package com.securitascash.service.transacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.dto.transacao.TransacaoForm;
import com.securitascash.enums.Movimento;
import com.securitascash.exception.ResourceNotFoundException;
import com.securitascash.model.Categoria;
import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
import com.securitascash.repository.CategoriaRepository;
import com.securitascash.repository.TransacaoRepository;
import com.securitascash.service.comentario.ComentarioService;
import com.securitascash.service.conta.ContaService;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {
    
    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaService contaService;

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    CategoriaRepository categoriaRepository;


    public void salvar(TransacaoForm transacaoForm, Long contaId){

        Conta conta = contaService.buscarPorId(contaId);

        Transacao transacao = new Transacao();
        transacao.setDescricao(transacaoForm.getDescricao());
        transacao.setValor(transacaoForm.getValor());
        transacao.setData(transacaoForm.getData());
        transacao.setConta(conta);

        Movimento movimento = transacaoForm.getMovimento().equals("Crédito") ? Movimento.CREDITO : Movimento.DEBITO; 
        transacao.setMovimento(movimento);

        transacao.setCategoria(transacaoForm.getCategoria());

        List<Comentario> comentarios = new ArrayList<>();
        if (transacaoForm.getComentarios() != null) {
            for (ComentarioForm c : transacaoForm.getComentarios()) {
                if (c.getTexto() != null && !c.getTexto().isBlank()) {
                    Comentario comentario = new Comentario();
                    comentario.setTexto(c.getTexto());
                    comentario.setTransacao(transacao);
                    comentarios.add(comentario);
                }
            }
        }

        transacao.setComentarios(comentarios);

        this.transacaoRepository.save(transacao);
    }



    @Transactional
    public void atualizar(Long id, TransacaoForm form) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        Categoria categoria = categoriaRepository.findById(form.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        transacao.setDescricao(form.getDescricao());
        transacao.setValor(form.getValor());
        transacao.setData(form.getData());

        Movimento movimento = form.getMovimento().equals("Crédito")  ? Movimento.CREDITO : Movimento.DEBITO; 

        transacao.setMovimento(movimento);
        transacao.setCategoria(categoria);

        transacaoRepository.save(transacao);
    }




    public List<Transacao> listarPorContaId(Long contaID){
        Conta conta = contaService.buscarPorId(contaID);
        return conta.getTransacoes();

    }

    public Transacao buscarPorId(Long id){
        return transacaoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Transação não encontrada com o id: " + id)
        );
    }


    @Transactional
    public Comentario adicionarComentario(Long transacaoId, ComentarioForm comentarioForm){
        Transacao transacao = this.buscarPorId(transacaoId);

        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioForm.getTexto());
        comentario.setTransacao(transacao);

        
        this.comentarioService.criarComentario(transacao, comentario);
        return comentario;

    }

    @Transactional
    public Comentario editarComentario(Long transacaoId, Long comentarioId, String texto){
        Transacao transacao = this.buscarPorId(transacaoId);
        return this.comentarioService.editarComentario(transacao, comentarioId, texto);
    }


    @Transactional
    public void excluirComentario(Long transacaoId, Long comentarioId){
        Transacao transacao = this.buscarPorId(transacaoId);

        this.comentarioService.excluirComentario(transacao, comentarioId);
        transacaoRepository.save(transacao);

    }

    public List<Comentario> listarComentarios (Long transacaoId){
        Transacao transacao = this.buscarPorId(transacaoId);
        return this.comentarioService.listarComentarios(transacao);
    }


}
