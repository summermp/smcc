package com.pac.smcc.service;

import com.pac.smcc.dao.ProductoDao;
import com.pac.smcc.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductoImpl implements ProductoService{

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProducto() {
        return productoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public Producto guardarproducto(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    @Transactional
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarProducto(Producto producto) {
        return productoDao.findById(producto.getId()).orElse(null);
    }
}
