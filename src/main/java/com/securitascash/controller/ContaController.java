package com.securitascash.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.securitascash.model.conta.Conta;
import com.securitascash.service.conta.ContaService;


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
}
