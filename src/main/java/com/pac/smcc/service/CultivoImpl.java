package com.pac.smcc.service;
import java.util.List;

import com.pac.smcc.dto.CultivoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pac.smcc.dao.CultivoDao;
import com.pac.smcc.domain.Cultivo;
@Service
public class CultivoImpl implements CultivoService{

    @Autowired
    private CultivoDao cultivoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cultivo> listarCultivo() {
        return (List<Cultivo>) cultivoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cultivo cultivo) {
        cultivoDao.save(cultivo);
    }

    @Override
    @Transactional
    public void eliminar(Cultivo cultivo) {
        cultivoDao.delete(cultivo);
    }

    @Override
    public void eliminarCultivo(Integer idcultivo) {
        cultivoDao.deleteCultivoById(idcultivo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CultivoDTO> listaCultivoUsuario(Integer idusuario) {
        return cultivoDao.findAllByUsuario(idusuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Cultivo buscarCultivo(Cultivo cultivo) {
        return cultivoDao.findById(cultivo.getId()).orElse(null);
    }

}
