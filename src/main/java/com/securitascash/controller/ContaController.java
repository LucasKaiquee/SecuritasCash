package com.securitascash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitascash.dto.conta.ContaForm;
import com.securitascash.model.conta.Conta;
import com.securitascash.model.usuario.Usuario; // MUDANÇA: Importar sua entidade Usuario
import com.securitascash.service.conta.ContaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    // MUDANÇA: O UsuarioController injetado não era necessário.
    
    @GetMapping
    public String getContas(Model model, Authentication authentication) { // MUDANÇA: Injetar Authentication
        // MUDANÇA: Obter o usuário diretamente do objeto de autenticação.
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Long usuarioId = usuarioLogado.getId();

        model.addAttribute("contaForm", new ContaForm());
        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("contas", contaService.listarContasByUser(usuarioId));
        return "contas/list";
    }

    @GetMapping("/criar")
    public String exibirFormulario(Model model, Authentication authentication) { // MUDANÇA: Injetar Authentication
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        model.addAttribute("contaForm", new ContaForm());
        model.addAttribute("usuarioId", usuarioLogado.getId());
        return "contas/form";
    }

    @PostMapping("/criar")
    public String adicionarConta(@Valid @ModelAttribute("contaForm") ContaForm contaForm, 
                                 BindingResult result,
                                 Model model,
                                 Authentication authentication, // MUDANÇA: Injetar Authentication
                                 RedirectAttributes redirectAttributes) {
        
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        if (result.hasErrors()) {
            // MUDANÇA: O try-catch foi removido. Se o usuário não estiver logado,
            // o Spring Security nem o deixará chegar aqui.
            model.addAttribute("usuarioId", usuarioLogado.getId());
            return "contas/form";
        }
        
        // Garante que a conta seja associada ao usuário correto
        contaForm.setUsuarioId(usuarioLogado.getId()); 
        contaService.criarConta(contaForm);
        redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return "redirect:/contas";
    }

    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model, Authentication authentication) {
        Conta conta = contaService.buscarPorId(id);
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        // MUDANÇA: Verificação de segurança!
        // Garante que o usuário só pode editar a própria conta.
        if (conta == null || !conta.getUsuario().getId().equals(usuarioLogado.getId())) {
            return "redirect:/contas";
        }

        ContaForm contaForm = new ContaForm(conta); // Um construtor no DTO pode simplificar isso
        model.addAttribute("contaForm", contaForm);
        return "contas/form";
    }

    // MUDANÇA: Método de edição implementado (era um PutMapping vazio).
    @PostMapping("/{id}/editar")
    public String alterarConta(@PathVariable Long id, 
                               @Valid @ModelAttribute("contaForm") ContaForm contaForm,
                               BindingResult result,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Se houver erros, retorna ao formulário para exibí-los.
            // O Spring reapresenta o contaForm com os dados já preenchidos.
            return "contas/form";
        }
        
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        contaForm.setUsuarioId(usuarioLogado.getId());

        // A lógica de autorização deve estar no seu service também, como uma camada extra.
        contaService.atualizarConta(id, contaForm);
        
        redirectAttributes.addFlashAttribute("mensagem", "Conta atualizada com sucesso!");
        return "redirect:/contas";
    }
}