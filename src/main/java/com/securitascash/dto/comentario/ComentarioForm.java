package com.securitascash.dto.comentario;

import org.hibernate.validator.constraints.Length;

import com.securitascash.validators.comentario.NoWhiteSpacesOnly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioForm {
    
    @NoWhiteSpacesOnly
    @Length(max = 250, message = "O texto deve ter no máximo 250 caracteres.")
    private String texto;
}