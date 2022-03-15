package com.pac.smcc.service;

import com.pac.smcc.dao.DispositivoDao;
import com.pac.smcc.domain.Dispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DispositivoImpl implements DispositivoService {

    @Autowired
    private DispositivoDao dispositivoDao;

    @Override
    public List<Dispositivo> listarDispositivo() {
        return dispositivoDao.findAll();
    }

    @Override
    public void guardar(Dispositivo dispositivo) {
        dispositivoDao.save(dispositivo);
    }

    @Override
    public void eliminar(Dispositivo dispositivo) {
        dispositivoDao.delete(dispositivo);
    }

    @Override
    public Dispositivo buscarDispositivo(Dispositivo dispositivo) {
        return dispositivoDao.findById(dispositivo.getId()).orElse(null);
    }

    @Override
    public void actualizarCultivo(Integer idcultivo) {
        dispositivoDao.updateCultivoById(idcultivo);
    }

    @Override
    public Dispositivo obtenerIdcultivo() {
       return dispositivoDao.getIdcultivo();
    }
}
