package com.securitascash.service.transacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.exception.ResourceNotFoundException;
import com.securitascash.model.Comentario;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
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


    public void criarTransacao(Transacao transacao){
        this.transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao editarTransacao(Transacao transacao){
        return transacao;
    }

    public List<Transacao> listarTransacoes(Long contaID){
        Conta conta = contaService.buscarContaPorId(contaID);
        return conta.getTransacoes();

    }

    public Transacao buscarTransacaoPorId(Long id){
        return transacaoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Transação não encontrada com o id: " + id)
        );
    }

    
    @Transactional
    public Comentario adicionarComentario(Long transacaoId, Comentario comentario){
        Transacao transacao = this.buscarTransacaoPorId(transacaoId);
        this.comentarioService.criarComentario(transacao, comentario);
        return comentario;

    }


    @Transactional
    public Comentario editarComentario(Long transacaoId, Long comentarioId, String texto){
        Transacao transacao = this.buscarTransacaoPorId(transacaoId);
        return this.comentarioService.editarComentario(transacao, comentarioId, texto);
    }


    @Transactional
    public void excluirComentario(Long transacaoId, Long comentarioId){
        Transacao transacao = this.buscarTransacaoPorId(transacaoId);

        this.comentarioService.excluirComentario(transacao, comentarioId);
        transacaoRepository.save(transacao);

    }

    public List<Comentario> listarComentarios (Long transacaoId){
        Transacao transacao = this.buscarTransacaoPorId(transacaoId);
        return this.comentarioService.listarComentarios(transacao);
    }


}
