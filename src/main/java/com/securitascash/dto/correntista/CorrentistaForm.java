package com.securitascash.dto.correntista;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrentistaForm {
    @NotBlank(message= "Nome é obrigatório.")
    private String nome;

    @NotBlank(message= "Senha é obrigatório.")
    private String senha;

    @NotBlank(message= "Email é obrigatório.")
    private String email;
    
    private boolean blocked;
}
