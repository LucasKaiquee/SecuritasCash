package com.securitascash.service.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.dto.orcamento.LinhaOrcamentoDTO;
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

    public void salvar(TransacaoForm transacaoForm, Long contaId) {

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

        if (transacaoForm.getComentario() != null) {
            Comentario comentario = new Comentario();
            comentario.setTexto(transacaoForm.getComentario().getTexto());
            comentario.setTransacao(transacao);
            comentarios.add(comentario);
        }

        transacao.setComentarios(comentarios);

        this.transacaoRepository.save(transacao);
    }

    @Transactional
    public void atualizar(Long id, TransacaoForm form) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        Categoria categoria = categoriaRepository.findByName(form.getCategoria().getName());

        transacao.setDescricao(form.getDescricao());
        transacao.setValor(form.getValor());
        transacao.setData(form.getData());

        Movimento movimento = form.getMovimento().equals("Crédito") ? Movimento.CREDITO : Movimento.DEBITO;

        transacao.setMovimento(movimento);
        transacao.setCategoria(categoria);

        transacaoRepository.save(transacao);
    }

    public Page<Transacao> listarPorContaId(Long contaID, Pageable pageable) {
        Conta conta = contaService.buscarPorId(contaID);
        return transacaoRepository.findAllByConta(conta, pageable);
    }

    public Page<Transacao> buscarFiltrado(Long contaID, Movimento movimento, Pageable pageable) {
        Conta conta = contaService.buscarPorId(contaID);
        return transacaoRepository.findByFilters(conta, movimento, pageable);
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transação não encontrada com o id: " + id)
        );
    }

    public List<Transacao> buscarExtrato(Long contaId, LocalDate inicio, LocalDate fim) {
        return transacaoRepository.findByContaIdAndDataBetweenOrderByDataAsc(contaId, inicio, fim);
    }

    @Transactional
    public Comentario adicionarComentario(Long transacaoId, ComentarioForm comentarioForm) {
        Transacao transacao = this.buscarPorId(transacaoId);

        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioForm.getTexto());
        comentario.setTransacao(transacao);

        this.comentarioService.criarComentario(transacao, comentario);
        return comentario;

    }

    @Transactional
    public Comentario editarComentario(Long transacaoId, Long comentarioId, String texto) {
        Transacao transacao = this.buscarPorId(transacaoId);
        return this.comentarioService.editarComentario(transacao, comentarioId, texto);
    }

    @Transactional
    public void excluirComentario(Long transacaoId, Long comentarioId) {
        Transacao transacao = this.buscarPorId(transacaoId);

        this.comentarioService.excluirComentario(transacao, comentarioId);
        transacaoRepository.save(transacao);

    }

    public List<Comentario> listarComentarios(Long transacaoId) {
        Transacao transacao = this.buscarPorId(transacaoId);
        return this.comentarioService.listarComentarios(transacao);
    }

    public Page<Transacao> buscarFiltradoPorPeriodo(Long contaId, Movimento movimento, LocalDate inicio, LocalDate fim, Pageable pageable) {
        if (movimento != null) {
            return transacaoRepository.findByContaIdAndMovimentoAndDataBetween(contaId, movimento, inicio, fim, pageable);
        }
        return transacaoRepository.findByContaIdAndDataBetween(contaId, inicio, fim, pageable);
    }

    public List<LinhaOrcamentoDTO> gerarOrcamentoAnual(Long contaId, int ano) {
        // 1. Definir o período do ano
        LocalDate inicioDoAno = LocalDate.of(ano, 1, 1);
        LocalDate fimDoAno = LocalDate.of(ano, 12, 31);

        // 2. Buscar todas as transações da conta para o ano especificado
        List<Transacao> transacoesDoAno = transacaoRepository.findByContaIdAndDataBetween(contaId, inicioDoAno, fimDoAno);

        // 3. Agrupar transações por categoria para otimizar o processamento
        Map<Categoria, List<Transacao>> transacoesPorCategoria = transacoesDoAno.stream()
                .collect(Collectors.groupingBy(Transacao::getCategoria));
                
        // 4. Buscar todas as categorias na ordem correta (Natureza, depois ordem)
        List<Categoria> categoriasOrdenadas = categoriaRepository.findAllByOrderByNaturezaAscOrdemAsc();

        List<LinhaOrcamentoDTO> orcamento = new ArrayList<>();

        // 5. Iterar sobre as categorias ordenadas para construir cada linha do orçamento
        for (Categoria categoria : categoriasOrdenadas) {
            BigDecimal[] valoresMensais = new BigDecimal[12];
            Arrays.fill(valoresMensais, BigDecimal.ZERO); // Inicializa todos os meses com 0

            List<Transacao> transacoesDaCategoria = transacoesPorCategoria.get(categoria);

            if (transacoesDaCategoria != null) {
                for (Transacao t : transacoesDaCategoria) {
                    int mes = t.getData().getMonthValue() - 1; // 0 para Janeiro, 1 para Fevereiro, etc.
                    valoresMensais[mes] = valoresMensais[mes].add(t.getValor());
                }
            }

            BigDecimal totalAnual = Arrays.stream(valoresMensais).reduce(BigDecimal.ZERO, BigDecimal::add);

            // Adiciona a linha ao orçamento apenas se houver movimentação no ano
            if (totalAnual.compareTo(BigDecimal.ZERO) != 0) {
                orcamento.add(new LinhaOrcamentoDTO(categoria, Arrays.asList(valoresMensais), totalAnual));
            }
        }

        return orcamento;
    }

}
