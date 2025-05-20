package com.securitascash.model.conta.dto;

import lombok.Data;

@Data
public class ContaForm {
    private String tipo;
    private String numero;
    private String descricao;
    private Integer diaDoFechamento;
    
}
