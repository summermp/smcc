package com.pac.smcc.dao;

import com.pac.smcc.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto, Integer> {

    @Modifying
    @Query(value="delete from producto where id =:idproducto",nativeQuery = true)
    public void deleteProducto(Integer idproducto);
}
