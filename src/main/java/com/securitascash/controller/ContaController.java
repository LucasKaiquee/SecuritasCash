package com.securitascash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitascash.dto.conta.ContaForm;
import com.securitascash.dto.usuario.UsuarioSessao;
import com.securitascash.model.conta.Conta;
import com.securitascash.service.conta.ContaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    ContaService contaService;

    @GetMapping
    public String getContas(Model model, HttpSession session) throws Exception {

        Long usuarioId = this.getUsuarioSessao(session).getId();
    
        List<Conta> contas = contaService.listarContasByUser(usuarioId);
        model.addAttribute("contas", contas);
        return "contas";
    }

    @GetMapping("/criar")
    public String exibirFormulario(Model model, HttpSession session) throws Exception {
        Long usuarioId = this.getUsuarioSessao(session).getId();

        model.addAttribute("contaForm", new ContaForm());
        model.addAttribute("usuarioId", usuarioId);

        return "criar-conta";
    }

    @PostMapping("/criar")
    public String adicionarConta(@ModelAttribute ContaForm contaForm, RedirectAttributes redirectAttributes) {
        contaService.criarConta(contaForm);
        redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return "redirect:/contas";
    }

    private UsuarioSessao getUsuarioSessao(HttpSession session){
        return session.getAttribute("usuarioLogado") != null
                ? (UsuarioSessao) session.getAttribute("usuarioLogado")
                : null;
    }
    
}


