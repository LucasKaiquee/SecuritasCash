package com.securitascash.service.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.securitascash.dto.categoria.CategoriaForm;
import com.securitascash.enums.Natureza;
import com.securitascash.model.Categoria;
import com.securitascash.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public void criarCategoria(CategoriaForm categoriaForm){
        Categoria categoria = new Categoria();
        
        categoria.setName(categoriaForm.getNome());
        categoria.setIsActive(categoriaForm.getAtivo());
        categoria.setNatureza(Natureza.valueOf(categoriaForm.getNatureza()));
        categoria.setOrdem(categoriaForm.getOrdem());

        this.categoriaRepository.save(categoria);
    }

    public Categoria editarCategoria(Long id, Categoria dto){
        Categoria categoria = this.buscar(id);

        System.out.println("CATEGORIA DO REPOSITORIO: " + categoria);

        System.out.println("CATEGORIA EDITADA: " + dto);

        categoria.setName(dto.getName());
        categoria.setIsActive(dto.getIsActive());
        categoria.setNatureza(dto.getNatureza());
        categoria.setOrdem(dto.getOrdem());

        return this.categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria buscarPorNome (String nome){
        return categoriaRepository.findByName(nome);
    }

    @Transactional
    public Categoria desativar(Long id){
        Categoria categoria = this.buscar(id);
        categoria.setIsActive(false);
        categoriaRepository.save(categoria);

        return categoria;
    }

    public Categoria buscar (Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException(" TA DANDO ERRO AQUI!"));
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
}
