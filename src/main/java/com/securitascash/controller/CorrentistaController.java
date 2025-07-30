package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.service.correntista.CorrentistaService;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;
    
    @GetMapping
    public ModelAndView listarCorrentistas(ModelAndView mav) {
        mav.addObject("correntistas", correntistaService.listarCorrentistas());
        mav.setViewName("correntistas/list");
        return mav;
    }

    @GetMapping("/novo")
    public ModelAndView exibirFormulario(ModelAndView mav) {
        mav.addObject("correntista", new Correntista());
        mav.setViewName("correntistas/form");
        return mav;
    }

    @PostMapping("/novo")
    public String criarCorrentista(@ModelAttribute Correntista correntista, RedirectAttributes attr) {
        correntistaService.criarCorrentista(correntista);
        attr.addFlashAttribute("mensagem", "Correntista criado com sucesso!");
        return "redirect:/correntistas";
    }
    
}
