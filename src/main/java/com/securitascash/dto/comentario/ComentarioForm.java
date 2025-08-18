package com.securitascash.dto.comentario;

import org.hibernate.validator.constraints.Length;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioForm {
    
    @NotBlank(message = "O texto não pode estar vazio.")
    @Length(max = 250, message = "O texto deve ter no máximo 250 caracteres.")
    private String texto;
}