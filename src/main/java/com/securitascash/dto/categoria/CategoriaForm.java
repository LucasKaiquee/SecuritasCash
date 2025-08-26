package com.securitascash.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaForm {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 20, message = "O nome não deve exceder 20 caracteres.")
    private String nome;
    private Boolean ativo = false;

    @NotNull(message = "A ordem deve ser um número positivo maior que zero.")
    @Positive(message = "A ordem deve ser um número positivo maior que zero.")
    private Integer ordem;
    private String natureza;
}
