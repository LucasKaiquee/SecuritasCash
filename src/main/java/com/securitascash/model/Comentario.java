package com.securitascash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    private Long id;
    private String texto;

    @ManyToOne
    private Transacao transacao;
}
