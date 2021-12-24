package com.pac.smcc.service;
import com.pac.smcc.domain.Parametros;
import java.util.List;
public interface ParametrosService{

    public Parametros guardar(Parametros parametros);

    public void eliminar(Parametros parametros);

    public Parametros buscarParametros(Parametros parametros);

    public List<Parametros> listaParametros(String fecha1, String fecha2, Integer idcultivo);

    public List<Parametros> listarParametros(Integer idusuario);
}
