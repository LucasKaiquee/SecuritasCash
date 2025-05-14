package com.securitascash.model.conta.inherit;

import com.securitascash.model.conta.Conta;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta{
    
}
