package com.securitascash.validators.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateAfterValidator implements ConstraintValidator<DateAfter, LocalDate>{

    private LocalDate dataReferencia;

    @Override
    public void initialize(DateAfter constraintAnnotation) {
        this.dataReferencia = LocalDate.parse(constraintAnnotation.value(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.isAfter(dataReferencia);  
    } 
    
}
