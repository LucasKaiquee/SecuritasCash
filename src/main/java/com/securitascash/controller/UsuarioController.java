package com.securitascash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    // A injeção do UsuarioService aqui não é mais necessária para o login.

    @GetMapping("/login")
    public String loginUsuario(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos.");
        }
        
        if (logout != null) {
            model.addAttribute("logout", "Você foi desconectado com sucesso.");
        }
        
        return "usuarioLogin";
    }

    @GetMapping("/403")
    public String acessoNegado() {
        return "/403"; 
    }

    // O método POST para login foi removido.
    // O método de logout também foi removido (o Spring Security cuida disso via URL /logout).
}