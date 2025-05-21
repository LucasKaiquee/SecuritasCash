package com.securitascash.utils;

import com.securitascash.dto.usuario.UsuarioSessao;

import jakarta.servlet.http.HttpSession;

public class Utils {

    public static UsuarioSessao getUsuarioSessao(HttpSession session) throws Exception{
        UsuarioSessao usuarioSessao = session.getAttribute("usuarioLogado") != null
                ? (UsuarioSessao) session.getAttribute("usuarioLogado")
                : null;
        if (usuarioSessao == null) {
            throw new Exception("Usuário não está logado.");
        }

        return usuarioSessao;
    }
}
