package com.pac.smcc.service;
import com.pac.smcc.domain.Dispositivo;
import java.util.List;
public interface DispositivoService {
    public List<Dispositivo> listarDispositivo();
    public void guardar(Dispositivo dispositivo);
    public void eliminar(Dispositivo dispositivo);
    public Dispositivo buscarDispositivo(Dispositivo dispositivo);
    public void actualizarCultivo(Integer idcultivo);
    Dispositivo obtenerIdcultivo();
}
