package com.securitascash.validators.comentario;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NoWhiteSpacesOnlyValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoWhiteSpacesOnly {
    String message() default "O texto não pode ser composto apenas por espaços em branco.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
