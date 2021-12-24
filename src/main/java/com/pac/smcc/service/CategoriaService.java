package com.pac.smcc.service;
import java.util.List;

import com.pac.smcc.domain.Categoria;
public interface CategoriaService {
    public List<Categoria> listarCategoria();
    
    public List<Categoria> listaCategoria();

    public void guardar(Categoria categoria);

    public Categoria guardarcat(Categoria categoria);

    public void eliminar(Categoria categoria);


    public Categoria buscarCategoria(Categoria categoria);
}
