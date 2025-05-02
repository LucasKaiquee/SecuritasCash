package com.securitascash.categoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.securitascash.enums.Natureza;
import com.securitascash.model.Categoria;
import com.securitascash.repository.CategoriaRepository;

@DataJpaTest
public class Creating {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    void createCategoriaSalario(){
        Categoria categoria = new Categoria();

        categoria.setIsActive(true);
        categoria.setName("Salario");
        categoria.setNatureza(Natureza.ENTRADA);
        categoria.setOrdem(2);

        this.categoriaRepository.save(categoria);

    }

    @Test
    void createCategoriaSaude(){
        Categoria categoria = new Categoria();

        categoria.setIsActive(true);
        categoria.setName("Saude");
        categoria.setNatureza(Natureza.SAIDA);
        categoria.setOrdem(1);

        this.categoriaRepository.save(categoria);
    }

    @Test
    void createCategoriaRendaFixa(){
        Categoria categoria = new Categoria();

        categoria.setIsActive(true);
        categoria.setName("Renda Fixa");
        categoria.setNatureza(Natureza.INVESTIMENTO);
        categoria.setOrdem(1);

        this.categoriaRepository.save(categoria);
    }
}
