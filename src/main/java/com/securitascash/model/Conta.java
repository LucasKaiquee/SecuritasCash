package com.securitascash.model;

import java.util.List;

import com.securitascash.enums.ContaTipo;

import jakarta.persistence.CascadeType;
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
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private ContaTipo tipo;

    private Integer diaDoFechamento;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<Transacao> transacoes;
}
