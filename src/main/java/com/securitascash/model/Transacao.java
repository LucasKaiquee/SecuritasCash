package com.securitascash.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.securitascash.enums.Movimento;
import com.securitascash.model.conta.Conta;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;
    private String descricao;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Movimento movimento;

    @ManyToOne
    private Conta conta;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "transacao")
    private List<Comentario> comentarios;

}
