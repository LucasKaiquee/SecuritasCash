package com.securitascash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/usuario")
public class UsuarioController {

    @GetMapping("/login")
    public String loginUsuario() {
        return "usuarioLogin";
    }
}
