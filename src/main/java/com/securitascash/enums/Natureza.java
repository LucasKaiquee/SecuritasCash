package com.securitascash.enums;

public enum Natureza {
    ENTRADA("Entrada"),
    INVESTIMENTO("Investimento"),
    SAIDA("Saída");

    private final String displayValue;
    
    private Natureza(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
