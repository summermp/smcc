package com.pac.smcc.web;
import com.pac.smcc.domain.Cultivo;
import com.pac.smcc.domain.Dispositivo;
import com.pac.smcc.domain.Usuario;
import com.pac.smcc.service.DispositivoService;
import com.pac.smcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DispositivoControlador {

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/dispositivo")
    public String listadispositivo(Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var dispositivos=dispositivoService.listarDispositivo();
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute("dispositivos", dispositivos);
        return "dispositivo";
    }

    @GetMapping("/agregardispositivo")
    public String agregar(Dispositivo dispositivo, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
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
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        dispositivo=dispositivoService.buscarDispositivo(dispositivo);
        model.addAttribute("dispositivo",dispositivo);
        model.addAttribute("datos_usuario",datos_usuario);
        return "modificardispositivo";
    }

    @GetMapping("/eliminardispositivo/{id}")
    public String eliminar(Dispositivo dispositivo){
        dispositivoService.eliminar(dispositivo);
        return "redirect:/dispositivo";
    }

    @PostMapping("/actualizarcultivo")
    public String actualizarcultivo(@RequestParam("id_actualizar_cultivo") Integer id_actualizar_cultivo) {
        if(id_actualizar_cultivo>0){
            dispositivoService.actualizarCultivo(id_actualizar_cultivo);
        }
            return "redirect:/dashboard";
    }

}
