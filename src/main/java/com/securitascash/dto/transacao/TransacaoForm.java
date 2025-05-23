package com.securitascash.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.securitascash.dto.comentario.ComentarioForm;
import com.securitascash.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoForm {
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String movimento;
    private Categoria categoria;
    private List<ComentarioForm> comentarios;
}
