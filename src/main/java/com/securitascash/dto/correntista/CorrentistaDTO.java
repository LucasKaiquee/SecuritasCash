package com.securitascash.dto.correntista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CorrentistaDTO {
    //TODO: Implementar validações
    @NotBlank(message= "Nome é obrigatório.")
    private String nome;

    @NotNull(message= "Senha é obrigatório.")
    private String senha;

    @NotBlank(message= "Email é obrigatório.")
    private String email;
    
    private boolean blocked;
}
