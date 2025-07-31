package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.securitascash.dto.categoria.CategoriaForm;
import com.securitascash.enums.Natureza;
import com.securitascash.service.categoria.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    CategoriaService service;

    @GetMapping
    public ModelAndView list (ModelAndView mav) {
        mav.setViewName("categorias/list");
        mav.addObject("categorias", service.listarCategorias());
        mav.addObject("naturezas", Natureza.values());
        return mav;
    }

    @GetMapping("/criar")
    public ModelAndView form(ModelAndView mav) {
        mav.setViewName("categorias/form");
        mav.addObject("naturezas", Natureza.values());
        mav.addObject("categoriaForm", new CategoriaForm());
        return mav;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute CategoriaForm categoriaForm, ModelAndView mav) {
        service.criarCategoria(categoriaForm);
        mav.setViewName("redirect:/categorias");
        return mav;
    }
}
