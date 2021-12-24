package com.pac.smcc.service;
import com.pac.smcc.dao.ParametrosDao;
import com.pac.smcc.domain.Parametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ParametroImpl implements ParametrosService{

    @Autowired
    private ParametrosDao parametrosDao;

    @Override
    public Parametros guardar(Parametros parametros) {
       return parametrosDao.save(parametros);
    }

    @Override
    public void eliminar(Parametros parametros) {
        parametrosDao.delete(parametros);
    }

    @Override
    public Parametros buscarParametros(Parametros parametros) {
        return parametrosDao.findById(parametros.getId()).orElse(null);
    }

    @Override
    public List<Parametros> listaParametros(String fecha1, String fecha2, Integer idcultivo) {
        return parametrosDao.findAllByFechahora(fecha1,fecha2, idcultivo);
    }

    @Override
    public List<Parametros> listarParametros(Integer idusuario) {
        return parametrosDao.findAllByCultivo(idusuario);
    }
}
