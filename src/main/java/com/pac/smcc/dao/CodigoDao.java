package com.pac.smcc.dao;

import com.pac.smcc.domain.Codigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodigoDao extends JpaRepository<Codigo, Integer> {
    @Query("select count(u) = 1 from Codigo u where codigo = ?1")
    public boolean findExistByCodigo(String codigo);
}
