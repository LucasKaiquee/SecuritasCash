package com.securitascash.model.usuario.inherit;

import com.securitascash.model.usuario.Usuario;

import java.util.List;

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
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario{
    public Administrador(Long id, String nome, String senha, String email, List<Conta> contas) {
        super(id, nome, senha, email, contas);
    }
}
