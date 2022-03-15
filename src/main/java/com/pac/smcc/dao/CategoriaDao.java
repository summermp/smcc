package com.pac.smcc.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pac.smcc.domain.Categoria;
public interface CategoriaDao extends JpaRepository<Categoria,Integer>{
    @Query(value="select * from categoria",nativeQuery = true)
    List<Categoria> findAllCategoria();
}
