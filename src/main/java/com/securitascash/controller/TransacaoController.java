package com.securitascash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.dto.transacao.TransacaoForm;
import com.securitascash.dto.usuario.UsuarioSessao;
import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
import com.securitascash.service.categoria.CategoriaService;
import com.securitascash.service.conta.ContaService;
import com.securitascash.service.transacao.TransacaoService;
import com.securitascash.service.usuario.UsuarioService;
import com.securitascash.utils.Utils;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("contas/{id}/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ContaService contaService;

    // Transações
    @GetMapping
    public ModelAndView listarPorConta(@PathVariable Long id, HttpSession session, ModelAndView mav) throws Exception {

        UsuarioSessao usuarioSessao = Utils.getUsuarioSessao(session);
        Conta conta = contaService.buscarPorId(id);

        if (!conta.getUsuario().getId().equals(usuarioSessao.getId())) {
            throw new ResponseStatusException( HttpStatus.FORBIDDEN);
        }

        List<Transacao> transacoes = transacaoService.listarPorContaId(id);
        mav.setViewName("transacoes/list");
        
        mav.addObject("transacoes", transacoes);
        mav.addObject("conta", conta);
        return mav;
    }


    @GetMapping("criar")
    public ModelAndView formulario(@PathVariable Long id, ModelAndView mav) {
        mav.setViewName("transacoes/form");
        
        mav.addObject("transacaoForm", new TransacaoForm()); 
        mav.addObject("contaId", id);
        mav.addObject("categorias", categoriaService.listarCategorias());
         
        return mav;
    }

    
    @PostMapping
    public ModelAndView salvar(@Valid @ModelAttribute TransacaoForm transacaoForm, BindingResult result,  @RequestParam("contaId") Long contaId, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("transacoes/form");
            mav.addObject("contaId", contaId);
            mav.addObject("categorias", categoriaService.listarCategorias());
            return mav;
        }

        transacaoService.salvar(transacaoForm, contaId);
        mav.setViewName("redirect:/contas/" + contaId + "/transacoes");
        return mav;
    }

    @PutMapping("/{transacaoId}/detalhes")
    public String atualizar(@PathVariable("id") Long contaId,
                                    @PathVariable("transacaoId") Long transacaoId,
                                    @ModelAttribute TransacaoForm dto) {

        transacaoService.atualizar(transacaoId, dto); 

        return "redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/detalhes";
    }


    
    //Comentários
    @GetMapping("/{transacaoId}/detalhes")
    public ModelAndView listarComentarios (@PathVariable Long transacaoId, ModelAndView mav, @PathVariable("id") Long contaId) {

        Transacao transacao = transacaoService.buscarPorId(transacaoId);
        
        mav.setViewName("transacoes/detalhes");

        mav.addObject("comentarios", transacao.getComentarios());
        mav.addObject("categorias", categoriaService.listarCategorias());
        mav.addObject("transacao", transacao);
        mav.addObject("comentarioForm", new ComentarioForm());
        
        return mav;
    }

    @PostMapping("/{transacaoId}/detalhes")
    public ModelAndView salvarComentario(@Valid @ModelAttribute ComentarioForm comentarioForm, BindingResult result,
                                @RequestParam Long transacaoId, @PathVariable("id") Long contaId, ModelAndView mav) {
                                    
        if (result.hasErrors()) {
            Transacao transacao = transacaoService.buscarPorId(transacaoId);
            mav.setViewName("transacoes/detalhes");
            mav.addObject("comentarios", transacao.getComentarios());
            mav.addObject("categorias", categoriaService.listarCategorias());
            mav.addObject("transacao", transacao);
            return mav;
        }

        transacaoService.adicionarComentario(transacaoId, comentarioForm);

        mav.setViewName("redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/detalhes" );
        return mav;
    }

    @PutMapping("/{transacaoId}/detalhes/{comentarioId}")
    public String editarComentario(
            @PathVariable("transacaoId") Long transacaoId,
            @PathVariable("comentarioId") Long comentarioId,
            @PathVariable("id") Long contaId,
            @RequestParam String texto) {

        transacaoService.editarComentario(transacaoId, comentarioId, texto);
        
        return "redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/detalhes" ;
    }

    @DeleteMapping("/{transacaoId}/detalhes/{comentarioId}")
    public String deletarComentario (
            @PathVariable("transacaoId") Long transacaoId,
            @PathVariable("comentarioId") Long comentarioId,
            @PathVariable("id") Long contaId){

        transacaoService.excluirComentario(transacaoId, comentarioId);

        return "redirect:/contas/" + contaId + "/transacoes/" + transacaoId + "/detalhes" ;
    }

    
}
