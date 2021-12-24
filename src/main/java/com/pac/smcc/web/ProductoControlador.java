package com.pac.smcc.web;
import com.pac.smcc.domain.Producto;
import com.pac.smcc.service.CategoriaService;
import com.pac.smcc.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/producto")
    public String incioProductos(Model model){
        var productos=productoService.listarProducto();
        model.addAttribute( "productos",productos);
        return "producto";
    }
    @GetMapping("/agregarproducto")
    public String agregar(Producto producto, Model model){
        model.addAttribute("categorias",categoriaService.listarCategoria());
        return "modificarproducto";
    }

    @PostMapping("/guardarproducto")
    public String guardar(Producto producto){
//        producto.setIdusuario(3);
        productoService.guardar(producto);
        return "redirect:/producto";
    }
    @GetMapping("/editarproducto/{id}")//ya existe lo asocia
    public String editar(Producto producto, Model model){
        producto=productoService.buscarProducto(producto);
        model.addAttribute("producto",producto);
        model.addAttribute("categorias",categoriaService.listarCategoria());
        return "modificarproducto";
    }

    @GetMapping("/eliminarproducto/{id}")
    public String eliminar(Producto producto){
        productoService.eliminar(producto);
        return "redirect:/producto";
    }
}
