package com.pac.smcc.web;
import com.pac.smcc.domain.Producto;
import com.pac.smcc.service.CategoriaService;
import com.pac.smcc.service.ProductoService;
import com.pac.smcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/producto")
    public String incioProductos(Model model){
        var productos=productoService.listarProducto();
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute( "productos",productos);
        return "producto";
    }
    @GetMapping("/agregarproducto")
    public String agregar(Producto producto, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        model.addAttribute("categorias",categoriaService.listarCategoria());
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
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
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        producto=productoService.buscarProducto(producto);
        model.addAttribute("producto",producto);
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute("categorias",categoriaService.listarCategoria());
        return "modificarproducto";
    }

    @GetMapping("/eliminarproducto/{id}")
    public String eliminar(@PathVariable(value="id") Integer idproducto){
        productoService.eliminarproducto(idproducto);
        return "redirect:/producto";
    }



}
