package com.securitascash.model;

import com.securitascash.enums.Natureza;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 20, message = "O nome não deve exceder 20 caracteres.")
    private String name;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive;

    @NotNull(message = "A ordem deve ser um número positivo maior que zero.")
    @Positive(message = "A ordem deve ser um número positivo maior que zero.")
    private Integer ordem;

    @Enumerated(EnumType.STRING)
    private Natureza natureza;

}
