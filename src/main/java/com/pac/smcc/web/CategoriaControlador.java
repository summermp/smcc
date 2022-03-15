package com.pac.smcc.web;
import com.pac.smcc.domain.Categoria;
import com.pac.smcc.service.CategoriaService;
import com.pac.smcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CategoriaControlador {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/categoria")
    public String incioCategorias(Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var categorias=categoriaService.listarCategoria();
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute( "categorias",categorias);
        return "categoria";
    }

    @GetMapping("/agregarcategoria")
    public String agregar(Categoria categoria, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        return "modificarcategoria";
    }

    @PostMapping("/guardarcategoria")
    public String guardar(Categoria categoria){
//        categoria.setIdusuario(3);
        categoriaService.guardar(categoria);
        return "redirect:/categoria";
    }
    @GetMapping("/editarcategoria/{id}")//ya existe lo asocia
    public String editar(Categoria categoria, Model model){
        categoria=categoriaService.buscarCategoria(categoria);
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute("categoria",categoria);
        return "modificarcategoria";
    }

    @GetMapping("/eliminarcategoria/{id}")
    public String eliminar(Categoria categoria){
        categoriaService.eliminar(categoria);
        return "redirect:/categoria";
    }

}
