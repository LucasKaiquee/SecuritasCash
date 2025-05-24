package com.securitascash.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller

public class CustomErrorController implements ErrorController{
    
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, ModelAndView mav) {
        
        Object statusObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = statusObj != null ? Integer.parseInt(statusObj.toString()) : 500;

        mav.setViewName("error");

        mav.addObject("codigo", statusCode);
        mav.addObject("mensagem", switch (statusCode) {
            case 404 -> "Página não encontrada.";
            case 500 -> "Erro interno do servidor.";
            default -> "Erro inesperado.";
        });

        return mav;
    }
}
