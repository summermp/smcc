package com.pac.smcc.web;
import com.pac.smcc.domain.Categoria;
import com.pac.smcc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CategoriaControlador {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria")
    public String incioCategorias(Model model){
        var categorias=categoriaService.listarCategoria();
        model.addAttribute( "categorias",categorias);
        return "categoria";
    }

    @GetMapping("/agregarcategoria")
    public String agregar(Categoria categoria){
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
        model.addAttribute("categoria",categoria);
        return "modificarcategoria";
    }

    @GetMapping("/eliminarcategoria/{id}")
    public String eliminar(Categoria categoria){
        categoriaService.eliminar(categoria);
        return "redirect:/categoria";
    }

}
