package com.securitascash.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securitascash.model.conta.Conta;
import com.securitascash.model.conta.inherit.CartaoDeCredito;
import com.securitascash.model.conta.inherit.ContaCorrente;
import com.securitascash.model.dto.ContaForm;
import com.securitascash.service.conta.ContaService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public String getContas(Model model) {
        List<Conta> contas = contaService.listarContas();
        model.addAttribute("contas", contas);
        return "contas";
    }

    @GetMapping("/criar")
    public String exibirFormulario(Model model) {
        model.addAttribute("contaForm", new ContaForm());
        return "criar-conta";
    }

    @PostMapping("/adicionar")
    public String adicionarConta(@ModelAttribute ContaForm contaForm, Model model) {
        Conta conta = null;
    
        switch (contaForm.getTipo()) {
            case "CORRENTE":
                conta = new ContaCorrente();
                break;
            case "CARTAO_CREDITO":
                CartaoDeCredito cartao = new CartaoDeCredito();
                cartao.setDiaDoFechamento(contaForm.getDiaDoFechamento());
                conta = cartao;
                break;
        }

        conta.setNumero(contaForm.getNumero());
        conta.setDescricao(contaForm.getDescricao());
        
        contaService.criarConta(conta);

        model.addAttribute("contas", contaService.listarContas());
        return "redirect:/contas"; // Redireciona para a lista após adicionar
    }
}


