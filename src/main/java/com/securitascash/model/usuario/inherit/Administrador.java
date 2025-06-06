package com.securitascash.model.usuario.inherit;

import com.securitascash.model.usuario.Usuario;

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
    
}
