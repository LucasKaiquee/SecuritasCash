package com.securitascash.model.conta.impl;

import com.securitascash.model.conta.Conta;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta{
    
}
