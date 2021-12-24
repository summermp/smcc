package com.pac.smcc.web;

import com.pac.smcc.dto.CultivoDTO;
import com.pac.smcc.service.CultivoService;
import com.pac.smcc.service.ParametrosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class ParametrosControlador {

    @Autowired
    private ParametrosService parametrosService;

    @Autowired
    private CultivoService cultivoService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/parametros")
    public String listadoParametros(Model model){
        Integer idusuario= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID USUARIO PARAMETRO: "+idusuario);
        var parametros=parametrosService.listarParametros(idusuario);
        var cultivos=cultivoService.listaCultivoUsuario(idusuario);
        model.addAttribute("parametros",parametros);
        model.addAttribute("cultivos",cultivos);

        for (CultivoDTO cd:cultivos) {
            System.out.println(cd.getId());
            System.out.println(cd.getNombre());
        }
        return "parametros";
    }

    @GetMapping("/parametrosfecha")
    public String listaParametros(@RequestParam("fecha1") String fecha1,@RequestParam("fecha2") String fecha2
            ,@RequestParam("idcultivo") Integer idcultivo,Model model){
        Integer idusuario= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID USUARIO CULTIVO - PARAMETRO: "+idusuario);
        var parametros=parametrosService.listaParametros(fecha1, fecha2,idcultivo);
        var cultivos=cultivoService.listaCultivoUsuario(idusuario);
        model.addAttribute("parametros",parametros);
        model.addAttribute("cultivos",cultivos);
        return "parametros";
    }
}
