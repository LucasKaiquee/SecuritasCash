package com.securitascash.model.conta.inherit;

import java.util.List;

import com.securitascash.model.Transacao;
import com.securitascash.model.conta.Conta;
import com.securitascash.model.usuario.Usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("CARTAO_CREDITO")
@Entity
public class CartaoDeCredito extends Conta{
    private Integer diaDoFechamento;

    public CartaoDeCredito(Long id, String numero, String descricao, String tipo, Usuario usuario, List<Transacao> transacoes, int diaDoFechamento){
        super(id, numero, descricao, tipo, usuario, transacoes);
        this.diaDoFechamento = diaDoFechamento;
    }
}
