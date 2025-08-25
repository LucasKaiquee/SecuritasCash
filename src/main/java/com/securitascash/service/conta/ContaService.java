package com.securitascash.service.conta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.dto.conta.ContaForm;
import com.securitascash.model.conta.Conta;
import com.securitascash.model.conta.inherit.CartaoDeCredito;
import com.securitascash.model.conta.inherit.ContaCorrente;
import com.securitascash.repository.ContaRepository;
import com.securitascash.repository.UsuarioRepository;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Conta criarConta(ContaForm contaForm){
        Conta conta = null;
    
        switch (contaForm.getTipo()) {
            case "Corrente":
                conta = new ContaCorrente();
                break;
            case "Cartão de Crédito":
                CartaoDeCredito cartao = new CartaoDeCredito();
                cartao.setDiaDoFechamento(contaForm.getDiaDoFechamento());
                conta = cartao;
                break;
        }

        conta.setTipo(contaForm.getTipo());
        conta.setNumero(contaForm.getNumero());
        conta.setDescricao(contaForm.getDescricao());

        conta.setUsuario(usuarioRepository.findById(contaForm.getUsuarioId()).orElse(null));

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

    public Conta buscarPorId (Long id){
        Conta conta = contaRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Conta não encontrada com o id: " + id)
        );
        return conta;
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
