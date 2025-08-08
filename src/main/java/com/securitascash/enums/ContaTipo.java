package com.securitascash.enums;

public enum ContaTipo {
    CORRENTE("Conta Corrente"),
    CARTAO_CREDITO("Cartão de Crédito");

    private final String displayValue;

    private ContaTipo(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}