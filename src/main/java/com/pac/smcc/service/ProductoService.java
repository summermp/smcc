package com.pac.smcc.service;
import com.pac.smcc.domain.Producto;
import java.util.List;

public interface ProductoService {

    public List<Producto> listarProducto();

    public void guardar(Producto producto);

    public Producto guardarproducto(Producto producto);

    public void eliminar(Producto producto);


    public Producto buscarProducto(Producto producto);

    void eliminarproducto(Integer idproducto);

}
