package com.securitascash.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaForm {
    private String nome;
    private Boolean ativo = false;
    private Integer ordem;
    private String natureza;
}
