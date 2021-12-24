package com.pac.smcc.service;

import com.pac.smcc.dao.CodigoDao;
import com.pac.smcc.domain.Codigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CodigoImpl implements CodigoService {

    @Autowired
    private CodigoDao codigoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Codigo> listarCodigo() {
        return codigoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Codigo codigo) {
        codigoDao.save(codigo);
    }

    @Override
    @Transactional
    public void eliminar(Codigo codigo) {
        codigoDao.delete(codigo);
    }

    @Override
    public Codigo buscarCodigo(Codigo codigo) {
        return codigoDao.findById(codigo.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCodigo(String codigo) {
        return codigoDao.findExistByCodigo(codigo);
    }


}
