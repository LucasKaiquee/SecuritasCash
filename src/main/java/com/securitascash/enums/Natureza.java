package com.securitascash.enums;

public enum Natureza {
    ENTRADA("Entrada"),
    SAIDA("Saída"),
    INVESTIMENTO("Investimento");

    private final String displayValue;
    
    private Natureza(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
