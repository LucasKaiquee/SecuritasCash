// package com.securitascash.config;

// import org.springframework.web.servlet.HandlerInterceptor;

// import com.securitascash.dto.usuario.UsuarioSessao;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

// public class LoginInterceptor implements HandlerInterceptor {

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         HttpSession session = request.getSession();
//         UsuarioSessao usuario = (UsuarioSessao) session.getAttribute("usuarioLogado");

//         if (usuario == null) {
//             response.sendRedirect("/usuario/login");
//             return false; 
//         }

//         return true; 
//     }
// }
