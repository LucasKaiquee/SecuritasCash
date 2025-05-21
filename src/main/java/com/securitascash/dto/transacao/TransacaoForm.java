package com.securitascash.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoForm {
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
