package com.securitascash.dto.conta;

import lombok.Data;

@Data
public class ContaForm {
    private String tipo;
    private String numero;
    private String descricao;
    private Integer diaDoFechamento;
    private Long usuarioId;
    
}
