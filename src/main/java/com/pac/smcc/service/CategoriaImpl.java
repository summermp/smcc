package com.pac.smcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pac.smcc.dao.CategoriaDao;
import com.pac.smcc.domain.Categoria;

@Service
public class CategoriaImpl implements CategoriaService{
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listarCategoria() {
        return categoriaDao.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listaCategoria() {
		return categoriaDao.findAllCategoria();
	}
	
    @Override
    @Transactional
    public void guardar(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public Categoria guardarcat(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void eliminar(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getId()).orElse(null);
    }


}
