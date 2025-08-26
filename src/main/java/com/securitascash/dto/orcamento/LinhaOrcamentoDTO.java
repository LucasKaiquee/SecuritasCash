package com.securitascash.dto.orcamento;

import java.math.BigDecimal;
import java.util.List;

import com.securitascash.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa uma linha na tabela do orçamento anual.
 * Contém a categoria, os totais mensais e o total anual.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinhaOrcamentoDTO {
    
    private Categoria categoria;
    private List<BigDecimal> valoresMensais; // 12 posições, uma para cada mês
    private BigDecimal totalAnual;

}