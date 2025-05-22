package com.securitascash.service.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitascash.model.Categoria;
import com.securitascash.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public void criarCategoria(Categoria categoria){
        this.categoriaRepository.save(categoria);
    }

    public Categoria editarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria buscarPorNome (String nome){
        return categoriaRepository.findByName(nome).get(0);
    }

    @Transactional
    public Categoria desativar(Long id){
        Categoria categoria = this.buscar(id);
        categoria.setIsActive(false);
        categoriaRepository.save(categoria);

        return categoria;
    }

    public Categoria buscar (Long id){
        return categoriaRepository.findById(id).orElse(null);
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
