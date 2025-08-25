package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitascash.dto.correntista.CorrentistaForm;
import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.service.correntista.CorrentistaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;
    
    @GetMapping
    public ModelAndView listarCorrentistas(ModelAndView mav, Pageable pageable, @RequestParam(required = false) Boolean blocked) {
        mav.addObject("pagina", correntistaService.listarFiltrado(blocked, pageable));
        mav.setViewName("correntistas/list");
        return mav;
    }

    @GetMapping("/novo")
    public ModelAndView exibirFormulario(ModelAndView mav) {
        mav.addObject("correntistaForm", new CorrentistaForm());
        mav.setViewName("correntistas/form");
        return mav;
    }

    @PostMapping("/novo")
    public ModelAndView criarCorrentista(@Valid @ModelAttribute CorrentistaForm correntista, BindingResult result, RedirectAttributes attr, ModelAndView mav) {

        if ( result.hasErrors() ) {
            mav.setViewName("correntistas/form");
            return mav;
        }
        
        correntistaService.criarCorrentista(correntista);
        attr.addFlashAttribute("mensagem", "Correntista criado com sucesso!");

        mav.setViewName("redirect:/correntistas");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView update (ModelAndView mav, @PathVariable Long id) {
        Correntista correntista = correntistaService.buscar(id);

        mav.setViewName("correntistas/form");
        mav.addObject("correntista", correntista);

        return mav;
    }

    @PutMapping("/editar/{id}")
    public ModelAndView update ( @Valid @ModelAttribute Correntista correntista, BindingResult result, @PathVariable Long id,  ModelAndView mav){
        if ( result.hasErrors() ) {
            mav.setViewName("correntistas/form");
            return mav;
        }
        correntistaService.editar(id, correntista);
        mav.setViewName("redirect:/correntistas");
        return mav;
    }
}
