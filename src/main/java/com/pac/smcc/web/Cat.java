package com.pac.smcc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pac.smcc.domain.Categoria;
import com.pac.smcc.service.CategoriaService;

@RestController
@RequestMapping("/apic")
public class Cat {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listado(){
        return categoriaService.listaCategoria();
    }

    @PostMapping("/registrar")
    public Categoria registrarCategoria(@RequestBody Categoria categoria){
        return categoriaService.guardarcat(categoria);
    }
}
