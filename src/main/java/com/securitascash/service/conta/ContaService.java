package com.securitascash.service.conta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.conta.Conta;
import com.securitascash.repository.ContaRepository;

@Service
public class ContaService {

    // OBS.: Discutir se será utilizada abstração para evitar duplicação de código
    // entre ContaCorrente e ContaCartao 

    @Autowired
    ContaRepository contaRepository;

    public String criarContaCorrente(){
        //TODO
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

    public List<Conta> listarContas(){
        //TODO
        return null;
    }


}
