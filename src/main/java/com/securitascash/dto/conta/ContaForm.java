package com.securitascash.dto.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContaForm {
    
    @NotBlank(message = "O tipo é obrigatório.")
    private String tipo;

    @NotBlank(message = "O número da conta é obrigatório.")
    @Size(min = 3, max = 20, message = "O número da conta deve ter entre 3 e 20 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "O número da conta deve conter apenas números.")
    private String numero;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;
    
    private Integer diaDoFechamento;
    
    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;
    
}
