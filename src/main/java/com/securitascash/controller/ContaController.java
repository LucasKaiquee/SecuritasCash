package com.securitascash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitascash.dto.conta.ContaForm;
import com.securitascash.model.conta.Conta;
import com.securitascash.service.conta.ContaService;
import com.securitascash.utils.Utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    ContaService contaService;

    @GetMapping
    public String getContas(Model model, HttpSession session) throws Exception {

        Long usuarioId = Utils.getUsuarioSessao(session).getId();
    
        List<Conta> contas = contaService.listarContasByUser(usuarioId);
        model.addAttribute("contas", contas);
        return "contas/list";
    }

    @GetMapping("/criar")
    public String exibirFormulario(Model model, HttpSession session) throws Exception {
        Long usuarioId = Utils.getUsuarioSessao(session).getId();

        model.addAttribute("contaForm", new ContaForm());
        model.addAttribute("usuarioId", usuarioId);

        return "contas/form";
    }

    @PostMapping("/criar")
    public String adicionarConta(@ModelAttribute ContaForm contaForm, RedirectAttributes redirectAttributes) {
        contaService.criarConta(contaForm);
        redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return "redirect:/contas";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView exibirFormularioEdicao(@PathVariable Long id, ModelAndView mav) {
        Conta conta = contaService.buscarPorId(id);
        if (conta == null) {
            mav.setViewName("redirect:/contas/list");
            return mav;
        }
        ContaForm contaForm = new ContaForm();

        contaForm.setNumero(conta.getNumero());
        contaForm.setDescricao(conta.getDescricao());
        contaForm.setTipo(conta.getTipo());
        contaForm.setUsuarioId(conta.getUsuario().getId());

        mav.addObject("contaForm", contaForm);
        
        mav.setViewName("contas/form");
        
        return mav;
    }

    @PutMapping("editar/{id}")
    public ModelAndView alterarConta(@PathVariable String id, ModelAndView mav) {
        
        
        return mav;
    }
    
}


