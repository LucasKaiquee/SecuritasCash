package com.securitascash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.securitascash.dto.transacao.TransacaoForm;
import com.securitascash.dto.usuario.UsuarioSessao;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
import com.securitascash.service.conta.ContaService;
import com.securitascash.service.transacao.TransacaoService;
import com.securitascash.service.usuario.UsuarioService;
import com.securitascash.utils.Utils;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("contas/{id}/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ContaService contaService;

    @GetMapping
    public String listarTransacoesPorConta(@PathVariable Long id, HttpSession session, Model model) throws Exception {

        UsuarioSessao usuarioSessao = Utils.getUsuarioSessao(session);
        Conta conta = contaService.buscarPorId(id);

        // Verifica se a conta pertence ao usuário logado
        if (!conta.getUsuario().getId().equals(usuarioSessao.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        List<Transacao> transacoes = transacaoService.listarTransacoes(id);
        model.addAttribute("transacoes", transacoes);
        model.addAttribute("conta", conta);
        return "transacoes";
    }

    @GetMapping("criar")
    public String criarTransacao(@PathVariable Long id, Model model) {
        model.addAttribute("transacaoForm", new TransacaoForm()); // DTO para o form
        model.addAttribute("contaId", id); // ID da conta associada
        return "transacoes/form";
    }

    @PostMapping("/criar")
    public String salvarTransacao(@ModelAttribute TransacaoForm transacaoForm,
                                @RequestParam("contaId") Long contaId) {

        Conta conta = contaService.buscarPorId(contaId);

        Transacao transacao = new Transacao();
        transacao.setDescricao(transacaoForm.getDescricao());
        transacao.setValor(transacaoForm.getValor());
        transacao.setData(transacaoForm.getData());
        transacao.setConta(conta);

        transacaoService.criarTransacao(transacao);

        return "redirect:/contas/" + contaId + "/transacoes";
    }

    

    
}
