package com.securitascash.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.model.Categoria;
import com.securitascash.validators.date.DateAfter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;     
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoForm {

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "Data é obrigatória")
    @Past(message = "Data deve ser no passado")
    @DateAfter(value = "01/01/2000", message = "Data deve ser posterior a 01/01/2000")
    private LocalDate data;

    private String movimento;
    private Categoria categoria;

    @Valid
    private ComentarioForm comentario;
}
