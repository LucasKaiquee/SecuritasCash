package com.securitascash.service.conta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.conta.Conta;
import com.securitascash.model.conta.dto.ContaForm;
import com.securitascash.model.conta.inherit.CartaoDeCredito;
import com.securitascash.model.conta.inherit.ContaCorrente;
import com.securitascash.repository.ContaRepository;
import com.securitascash.repository.UsuarioRepository;

@Service
public class ContaService {

    // OBS.: Discutir se será utilizada abstração para evitar duplicação de código
    // entre ContaCorrente e ContaCartao 

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Conta criarConta(ContaForm contaForm){
        Conta conta = null;
    
        switch (contaForm.getTipo()) {
            case "CORRENTE":
                conta = new ContaCorrente();
                break;
            case "CARTAO_CREDITO":
                CartaoDeCredito cartao = new CartaoDeCredito();
                cartao.setDiaDoFechamento(contaForm.getDiaDoFechamento());
                conta = cartao;
                break;
        }

        conta.setNumero(contaForm.getNumero());
        conta.setDescricao(contaForm.getDescricao());

        //Mockando o usuário
        conta.setUsuario(usuarioRepository.findById(4L).orElse(null));

        contaRepository.save(conta);
        return null;
    }

    public String editarContaCorrente(){
        //TODO
        return null;
    }

    public String excluirContaCorrente(){
        //TODO
        return null;
    }


    public String criarContaCartao(){
        //TODO
        return null;
    }

    public String editarContaCartao(){
        //TODO
        return null;
    }

    public String excluirContaCartao(){
        //TODO
        return null;
    }

    public List<Conta> listarContasByUser(Long userId){
        List<Conta> contas = contaRepository.findContaByUsuario_id(userId);
        return contas;
    }

    public List<Conta> listarContas(){
        List<Conta> contas = contaRepository.findAll();
        return contas;
    }


}
