package com.pac.smcc.service;
import com.pac.smcc.domain.Codigo;
import com.pac.smcc.domain.Usuario;

import java.util.List;

public interface CodigoService {
    public List<Codigo> listarCodigo();

    public void guardar(Codigo codigo);

    public void eliminar(Codigo codigo);

    public Codigo buscarCodigo(Codigo codigo);

    public boolean existeCodigo(String codigo);

}
