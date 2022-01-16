package com.pac.smcc.service;
import com.pac.smcc.dao.ParametrosDao;
import com.pac.smcc.domain.Parametros;
import com.pac.smcc.dto.ParametroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ParametroImpl implements ParametrosService{

    @Autowired
    private ParametrosDao parametrosDao;

    @Override
    @Transactional
    public Parametros guardar(Parametros parametros) {
       return parametrosDao.save(parametros);
    }

    @Override
    @Transactional
    public void eliminar(Parametros parametros) {
        parametrosDao.delete(parametros);
    }

    @Override
    @Transactional(readOnly = true)
    public Parametros buscarParametros(Parametros parametros) {
        return parametrosDao.findById(parametros.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parametros> listaParametros(String fecha1, String fecha2, Integer idcultivo) {
        return parametrosDao.findAllByFechahora(fecha1,fecha2, idcultivo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParametroDTO> listarParametros(Integer idusuario) {
        return parametrosDao.findAllByCultivo(idusuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParametroDTO> ultimaMedicion(Integer idusuario) {
        return parametrosDao.findParametros(idusuario);
    }
}
