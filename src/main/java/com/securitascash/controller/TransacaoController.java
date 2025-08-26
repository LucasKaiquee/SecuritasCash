package com.securitascash.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.dto.orcamento.ChartDatasetDTO;
import com.securitascash.dto.orcamento.LinhaOrcamentoDTO;
import com.securitascash.dto.transacao.TransacaoForm;
import com.securitascash.dto.usuario.UsuarioSessao;
import com.securitascash.enums.Movimento;
import com.securitascash.enums.Natureza;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
import com.securitascash.service.categoria.CategoriaService;
import com.securitascash.service.conta.ContaService;
import com.securitascash.service.transacao.TransacaoService;
import com.securitascash.service.usuario.UsuarioService;
import com.securitascash.utils.Utils;
import java.util.stream.IntStream;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("contas/{id}/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ContaService contaService;

    // Transações
    @GetMapping
    public ModelAndView listarPorConta(
            @PathVariable Long id,
            Authentication authentication,
            ModelAndView mav,
            @PageableDefault(page = 0, size = 7) Pageable pageable,
            @RequestParam(required = false) Movimento movimento,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim
    ) throws Exception {

        Conta conta = contaService.buscarPorId(id);

        LocalDate inicio;
        LocalDate fim;

        if (dataInicio != null && dataFim != null) {
            inicio = LocalDate.parse(dataInicio);
            fim = LocalDate.parse(dataFim);
        } else {
            inicio = LocalDate.now().withDayOfMonth(1);
            fim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        Page<Transacao> transacoes = transacaoService.buscarFiltradoPorPeriodo(id, movimento, inicio, fim, pageable);

        mav.setViewName("transacoes/list");
        mav.addObject("pagina", transacoes);
        mav.addObject("conta", conta);
        mav.addObject("inicio", inicio);
        mav.addObject("fim", fim);

        return mav;
    }

    @GetMapping("criar")
    public ModelAndView formulario(@PathVariable Long id, ModelAndView mav) {
        mav.setViewName("transacoes/form");

        mav.addObject("transacaoForm", new TransacaoForm());
        mav.addObject("contaId", id);
        mav.addObject("categorias", categoriaService.listarCategorias());
        mav.addObject("naturezas", Natureza.values());

        return mav;
    }

    @PostMapping
    public ModelAndView salvar(
            @Valid @ModelAttribute TransacaoForm transacaoForm,
            BindingResult result,
            @RequestParam("contaId") Long contaId,
            ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("transacoes/form");
            mav.addObject("contaId", contaId);
            mav.addObject("categorias", categoriaService.listarCategorias());
            mav.addObject("naturezas", Natureza.values());
            return mav;
        }

        transacaoService.salvar(transacaoForm, contaId);
        mav.setViewName("redirect:/contas/" + contaId + "/transacoes");
        return mav;
    }

    @GetMapping("/editar/{transacaoId}")
    public ModelAndView update(ModelAndView mav, @PathVariable("transacaoId") Long id, @PathVariable("id") Long contaId) {
        Transacao transacao = transacaoService.buscarPorId(id);

        mav.setViewName("transacoes/form");
        mav.addObject("transacao", transacao);
        mav.addObject("contaId", contaId);
        mav.addObject("categorias", categoriaService.listarCategorias());
        mav.addObject("naturezas", Natureza.values());

        return mav;
    }

    @PutMapping("/editar/{transacaoId}")
    public String atualizar(@PathVariable("id") Long contaId,
            @PathVariable("transacaoId") Long transacaoId,
            @ModelAttribute TransacaoForm dto) {

        transacaoService.atualizar(transacaoId, dto);

        return "redirect:/contas/" + contaId + "/transacoes";
    }

    //Comentários
    @GetMapping("/{transacaoId}/comentarios")
    public ModelAndView listarComentarios(@PathVariable Long transacaoId, ModelAndView mav, @PathVariable("id") Long contaId) {

        Transacao transacao = transacaoService.buscarPorId(transacaoId);

        mav.setViewName("transacoes/comentarios");

        mav.addObject("comentarios", transacao.getComentarios());
        mav.addObject("categorias", categoriaService.listarCategorias());
        mav.addObject("transacao", transacao);
        mav.addObject("comentarioForm", new ComentarioForm());

        return mav;
    }

    @PostMapping("/{transacaoId}/comentarios")
    public ModelAndView salvarComentario(@Valid @ModelAttribute ComentarioForm comentarioForm, BindingResult result,
            @RequestParam Long transacaoId, @PathVariable("id") Long contaId, ModelAndView mav) {

        if (result.hasErrors()) {
            Transacao transacao = transacaoService.buscarPorId(transacaoId);
            mav.setViewName("transacoes/comentarios");
            mav.addObject("comentarios", transacao.getComentarios());
            mav.addObject("transacao", transacao);
            return mav;
        }

        transacaoService.adicionarComentario(transacaoId, comentarioForm);

        mav.setViewName("redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/comentarios");
        return mav;
    }

    @PutMapping("/{transacaoId}/comentarios/{comentarioId}")
    public ModelAndView editarComentario(
            @Valid @ModelAttribute ComentarioForm comentarioForm,
            BindingResult result,
            @PathVariable("transacaoId") Long transacaoId,
            @PathVariable("comentarioId") Long comentarioId,
            @PathVariable("id") Long contaId,
            @RequestParam String texto,
            ModelAndView mav) {
        System.out.println("passei aqui");
        if (result.hasErrors()) {
            System.out.println("passei aqui 2");
            Transacao transacao = transacaoService.buscarPorId(transacaoId);
            mav.setViewName("transacoes/comentarios");
            mav.addObject("comentarios", transacao.getComentarios());
            mav.addObject("transacao", transacao);
            return mav;
        }

        transacaoService.editarComentario(transacaoId, comentarioId, texto);

        mav.setViewName("redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/comentarios");

        return mav;
    }

    @DeleteMapping("/{transacaoId}/comentarios/{comentarioId}")
    public String deletarComentario(
            @PathVariable("transacaoId") Long transacaoId,
            @PathVariable("comentarioId") Long comentarioId,
            @PathVariable("id") Long contaId) {

        transacaoService.excluirComentario(transacaoId, comentarioId);

        return "redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/comentarios";
    }

    @GetMapping("/orcamento-anual") 
    public ModelAndView orcamentoAnual(
            @PathVariable Long id,
            @RequestParam(required = false) Integer ano,
            Authentication authentication,
            ModelAndView mav
    ) throws Exception {

        Conta conta = contaService.buscarPorId(id);
    

        int anoSelecionado = (ano != null) ? ano : LocalDate.now().getYear();

        // 1. Busca os dados base (como antes)
        List<LinhaOrcamentoDTO> orcamentoBase = transacaoService.gerarOrcamentoAnual(id, anoSelecionado);

        // 2. NOVO: Agrupa a lista em um Mapa, onde a chave é a natureza da categoria
        // Usamos LinkedHashMap para manter a ordem (Entradas -> Saídas -> Investimentos)
        Map<String, List<LinhaOrcamentoDTO>> orcamentoAgrupado = orcamentoBase.stream()
                .collect(Collectors.groupingBy(
                        linha -> linha.getCategoria().getNatureza().getDisplayValue(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        // Lista de anos para o <select> ...
        int anoCorrente = LocalDate.now().getYear();
        List<Integer> anosDisponiveis = IntStream.rangeClosed(anoCorrente - 5, anoCorrente + 1)
                .boxed().sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());

        // 3. Envia o Mapa agrupado para a view em vez da lista simples
        mav.setViewName("transacoes/orcamentoAnual");
        mav.addObject("conta", conta);
        mav.addObject("orcamentoAgrupado", orcamentoAgrupado); // <--- MUDANÇA AQUI
        mav.addObject("anoSelecionado", anoSelecionado);
        mav.addObject("anosDisponiveis", anosDisponiveis);

        return mav;
    }

    @GetMapping("/orcamento-grafico")
    public ModelAndView orcamentoGrafico(
            @PathVariable Long id,
            @RequestParam(required = false) Integer ano,
            Authentication authentication,
            ModelAndView mav
    ) throws Exception {

        Conta conta = contaService.buscarPorId(id);

        // 2. Define o ano e busca os dados base
        int anoSelecionado = (ano != null) ? ano : LocalDate.now().getYear();
        List<LinhaOrcamentoDTO> orcamentoBase = transacaoService.gerarOrcamentoAnual(id, anoSelecionado);

        // 3. Prepara os dados para o formato do Chart.js
        // Labels para o eixo X (Meses)
        List<String> labels = Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

        // Datasets (cada categoria será um dataset/linha no gráfico)
        List<ChartDatasetDTO> datasets = new ArrayList<>();
        Random rand = new Random();

        for (LinhaOrcamentoDTO linha : orcamentoBase) {
            // Gera uma cor aleatória para cada categoria
            int r = rand.nextInt(200);
            int g = rand.nextInt(200);
            int b = rand.nextInt(200);
            String color = String.format("rgba(%d, %d, %d, 1)", r, g, b);

            datasets.add(new ChartDatasetDTO(
                    linha.getCategoria().getName(),
                    linha.getValoresMensais(),
                    color
            ));
        }

        // 4. Prepara a lista de anos para o <select>
        int anoCorrente = LocalDate.now().getYear();
        List<Integer> anosDisponiveis = IntStream.rangeClosed(anoCorrente - 5, anoCorrente + 1)
                .boxed().sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());

        // 5. Envia os dados para a view
        mav.setViewName("transacoes/orcamentoGrafico");
        mav.addObject("conta", conta);
        mav.addObject("labels", labels);
        mav.addObject("datasets", datasets);
        mav.addObject("anoSelecionado", anoSelecionado);
        mav.addObject("anosDisponiveis", anosDisponiveis);

        return mav;
    }

}
