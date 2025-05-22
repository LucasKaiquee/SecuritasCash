package com.securitascash.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.securitascash.enums.Natureza;
import com.securitascash.model.Categoria;
import com.securitascash.model.usuario.inherit.Correntista;
import com.securitascash.repository.CategoriaRepository;
import com.securitascash.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadDataCategoria(CategoriaRepository repositorio) {
        return args -> {
            if (repositorio.count() == 0) {
                repositorio.saveAll(List.of(
                    new Categoria(null, "Salário", true, 6, Natureza.ENTRADA),
                    new Categoria(null, "Cashback", true, 6, Natureza.ENTRADA),
                    new Categoria(null, "Resgate Investimento", true, 7, Natureza.ENTRADA),
                    new Categoria(null, "Outras Entradas", true, 7, Natureza.ENTRADA),

                    new Categoria(null, "Saúde e Remédios", true, 4, Natureza.SAIDA),
                    new Categoria(null, "Academia e Personal", true, 3, Natureza.SAIDA),
                    new Categoria(null, "Carros e Uber", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Educação e Cursos", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Lazer e Turismo", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Condomínio", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Energia", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Celular", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Internet", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Itens Pessoais", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Feira", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Casa", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Impostos", true, 5, Natureza.SAIDA),
                    new Categoria(null, "Outros Gastos", true, 5, Natureza.SAIDA),
                    
                    new Categoria(null, "Aporte Renda Fixa", true, 5, Natureza.INVESTIMENTO),
                    new Categoria(null, "Aporte Renda Variável", true, 5, Natureza.INVESTIMENTO),
                    new Categoria(null, "Aporte Reserva Emergência", true, 5, Natureza.INVESTIMENTO),
                    new Categoria(null, "Aporte Previdência", true, 5, Natureza.INVESTIMENTO)
                ));
            }
        };
    }

    @Bean
    CommandLineRunner loadDataUsuarioCorrentista(UsuarioRepository repositorio) {
        return args -> {
            if (repositorio.count() == 0) {
                repositorio.save(new Correntista(null, "Luiz", "a", "lf@email.com", null));
                repositorio.save(new Correntista(null, "Luiz", "a", "lf2@email.com", null));
            }
        };
    }
}
