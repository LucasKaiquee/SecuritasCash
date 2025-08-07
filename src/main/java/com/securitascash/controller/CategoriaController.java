package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.securitascash.dto.categoria.CategoriaForm;
import com.securitascash.enums.Natureza;
import com.securitascash.model.Categoria;
import com.securitascash.service.categoria.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    CategoriaService service;

    @GetMapping
    public ModelAndView list (
        ModelAndView mav, 
        @PageableDefault(page = 0, size = 7, sort = "name") Pageable pageable,
        @RequestParam(required = false) Natureza natureza,
        @RequestParam(required = false) Boolean isActive
        ) {

        Page<Categoria> categorias = service.listarComFiltro(natureza, isActive, pageable);

        mav.setViewName("categorias/list");
        mav.addObject("pagina", categorias);

        mav.addObject("naturezas", Natureza.values());
        mav.addObject("naturezaSelecionada", natureza);
        mav.addObject("ativoSelecionado", isActive);
        
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
    public ModelAndView create(@Valid @ModelAttribute CategoriaForm categoriaForm, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            mav.setViewName("categorias/form");
            mav.addObject("naturezas", Natureza.values());
            return mav;
        }
        service.criarCategoria(categoriaForm);
        mav.setViewName("redirect:/categorias");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView update (ModelAndView mav, @PathVariable Long id) {
        Categoria categoria = service.buscar(id);

        mav.setViewName("categorias/form");
        mav.addObject("categoria", categoria);
        mav.addObject("naturezas", Natureza.values());

        return mav;
    }

    @PutMapping("/{id}")
    public ModelAndView update (@ModelAttribute Categoria categoria, @PathVariable Long id,  ModelAndView mav){

        service.editarCategoria(id, categoria);
        mav.setViewName("redirect:/categorias");
        return mav;
    }

    @DeleteMapping("/excluir/{id}")
    public ModelAndView delete (@PathVariable Long id, ModelAndView mav) {
        service.excluir(id);
        mav.setViewName("redirect:/categorias");
        return mav;
    }
}
