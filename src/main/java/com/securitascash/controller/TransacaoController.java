package com.securitascash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.securitascash.model.Transacao;
import com.securitascash.service.transacao.TransacaoService;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    //TODO: Implementar resto da lógica nesse Controller e no transacoes.html

    @GetMapping("/transacoes")
    public String listarTransacoes(Model model) {
        List<Transacao> transacoes = transacaoService.listarTransacoes(1L);
        model.addAttribute("transacoes", transacoes);
        return "transacoes";
    }
}
