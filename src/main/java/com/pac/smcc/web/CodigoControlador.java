package com.pac.smcc.web;
import com.pac.smcc.domain.Codigo;
import com.pac.smcc.service.CodigoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class CodigoControlador {

    @Autowired
    private CodigoService codigoService;

    @GetMapping("/codigo")
    public String incioCodigos(Model model){
        var codigos=codigoService.listarCodigo();
        model.addAttribute( "codigos",codigos);
        return "codigo";
    }

    @GetMapping("/agregarcodigo")
    public String agregar(Codigo codigo){
        return "modificarcodigo";
    }

    @PostMapping("/guardarcodigo")
    public String guardar(Codigo codigo,@RequestParam("codregion") String codregion,  @RequestParam("dni") String dni, Model model){
        String fechahora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.error("No existe el codigo: "+codigo.getCodigo());
        StringBuilder codigogenerado=new StringBuilder();
        codigogenerado.append(codregion).append(dni);
        if(!codigoService.existeCodigo(String.valueOf(codigogenerado))){
            log.error("CODIGO: "+codigogenerado);
            codigo.setCodigo(String.valueOf(codigogenerado));
            codigo.setFecha(fechahora);
            codigoService.guardar(codigo);
        }else{
            log.error("Si existe el codigo: "+codigo.getCodigo());
            model.addAttribute("error","El codigo ya esta registrado");
            return "modificarcodigo";
        }
        return "redirect:/codigo";
    }
    @GetMapping("/editarcodigo/{id}")//ya existe lo asocia
    public String editar(Codigo codigo, Model model){
        codigo=codigoService.buscarCodigo(codigo);
        model.addAttribute("codigo",codigo);
        return "modificarcodigo";
    }

    @GetMapping("/eliminarcodigo/{id}")
    public String eliminar(Codigo codigo){
        codigoService.eliminar(codigo);
        return "redirect:/codigo";
    }

    @GetMapping("/validarcodigo")
    public String validarcodigo(@RequestParam("codigoregistro") String codigo, Model model){
        if(!codigoService.existeCodigo(String.valueOf(codigo))){
            log.error("CODIGO NO REGISTRADO COMUNIQUESE CON EL ADMINISTRADOR: "+codigo);
            model.addAttribute("error","CODIGO NO REGISTRADO COMUNIQUESE CON EL ADMINISTRADOR");
            return "validarcodigo";
        }else{
            model.addAttribute("error","Ya esta registrado");
            return "/login";
        }
    }
}
