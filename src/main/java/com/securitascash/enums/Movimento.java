package com.securitascash.enums;

public enum Movimento {

    CREDITO("Crédito"),
    DEBITO("Débito");

    private final String displayValue;

    private Movimento(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
