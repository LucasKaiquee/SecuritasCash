package com.securitascash.model.usuario.inherit;

import com.securitascash.model.usuario.Usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("CORRENTISTA")
public class Correntista extends Usuario{
    private boolean isBlocked;
}
