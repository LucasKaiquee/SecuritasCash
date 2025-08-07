package com.securitascash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.securitascash.enums.Natureza;
import com.securitascash.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    Categoria findByName(String name);

    @Query("SELECT c FROM Categoria c WHERE " +
        "(:natureza is null OR c.natureza = :natureza) AND " +
        "(:ativo is null OR c.isActive = :ativo)") 
    Page<Categoria> findByFilters( @Param("natureza") Natureza natureza, @Param("ativo") Boolean ativo,  Pageable pageable);

}
