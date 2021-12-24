package com.pac.smcc.service;
import java.util.List;

import com.pac.smcc.domain.Cultivo;
import com.pac.smcc.dto.CultivoDTO;

public interface CultivoService {
    public List<Cultivo> listarCultivo();

    public void guardar(Cultivo cultivo);

    public void eliminar(Cultivo cultivo);

    public void eliminarCultivo(Integer idcultivo);

    public Cultivo buscarCultivo(Cultivo cultivo);

    public List<CultivoDTO> listaCultivoUsuario(Integer idusuario);
}
