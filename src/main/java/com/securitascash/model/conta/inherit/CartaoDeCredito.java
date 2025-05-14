package com.securitascash.model.conta.inherit;

import com.securitascash.model.conta.Conta;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("CARTAO_CREDITO")
@Entity
public class CartaoDeCredito extends Conta{
    private Integer diaDoFechamento;
}
