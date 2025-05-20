package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.securitascash.model.usuario.dto.UsuarioSessao;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.securitascash.model.usuario.Usuario;
import com.securitascash.service.usuario.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginUsuario(Model model) {
        return "usuarioLogin";
    }

    @PostMapping("/login")
    public String loginUsuarioPost(@RequestParam String email,
                        @RequestParam String password, Model model, HttpSession session) {
        
        Usuario usuario = usuarioService.login(email, password);

        if (usuario != null) {
            UsuarioSessao usuarioSessao = new UsuarioSessao();
            usuarioSessao.setId(usuario.getId());
            usuarioSessao.setNome(usuario.getNome());
    
            session.setAttribute("usuarioLogado", usuarioSessao);
            return "redirect:/contas";
        }

        model.addAttribute("error", "Usuário ou senha inválidos");
        return "usuarioLogin";
    }
    
}
