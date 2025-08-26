package com.securitascash.dto.orcamento;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ChartDatasetDTO {
    private String label; // Nome da categoria (ex: "Salário")
    private List<BigDecimal> data; // Lista com os 12 valores mensais
    private String borderColor; // Cor da linha
    private String backgroundColor; // Cor do fundo (com transparência)
    private boolean fill = false;
    private double tension = 0.1;

    public ChartDatasetDTO(String label, List<BigDecimal> data, String color) {
        this.label = label;
        this.data = data;
        this.borderColor = color;
        this.backgroundColor = color.replace("1)", "0.2)"); // Adiciona transparência
    }
}