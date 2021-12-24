package com.pac.smcc.dao;

import com.pac.smcc.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Integer> {
}
