package com.pac.smcc.web;
import com.pac.smcc.domain.Dispositivo;
import com.pac.smcc.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class DispositivoControlador {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivo")
    public String listadispositivo(Model model){
        var dispositivos=dispositivoService.listarDispositivo();
        model.addAttribute("dispositivos", dispositivos);
        return "dispositivo";
    }

    @GetMapping("/agregardispositivo")
    public String agregar(Dispositivo dispositivo){
        return "modificardispositivo";
    }

    @PostMapping("/guardardispositivo")
    public String guardar(Dispositivo dispositivo){
//        dispositivo.setIdusuario(3);
        dispositivoService.guardar(dispositivo);
        return "redirect:/dispositivo";
    }
    @GetMapping("/editardispositivo/{id}")//ya existe lo asocia
    public String editar(Dispositivo dispositivo, Model model){
        dispositivo=dispositivoService.buscarDispositivo(dispositivo);
        model.addAttribute("dispositivo",dispositivo);
        return "modificardispositivo";
    }

    @GetMapping("/eliminardispositivo/{id}")
    public String eliminar(Dispositivo dispositivo){
        dispositivoService.eliminar(dispositivo);
        return "redirect:/dispositivo";
    }

}
