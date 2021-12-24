package com.pac.smcc.web;
import com.pac.smcc.domain.Cultivo;
import com.pac.smcc.domain.Usuario;
import com.pac.smcc.service.CategoriaService;
import com.pac.smcc.service.CultivoService;
import com.pac.smcc.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
@Controller
@Slf4j
public class CultivoControlador {
    @Autowired
    private CultivoService cultivoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/cultivo")
    public String incioCultivo(Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var cultivos=cultivoService.listaCultivoUsuario(idus);
        model.addAttribute( "cultivos",cultivos);
        return "cultivo";
    }

    @GetMapping("/agregarcultivo")
    public String agregar(Cultivo cultivo, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID USUARIO ADD: "+idus);

        model.addAttribute("productos",productoService.listarProducto());
        model.addAttribute("idus",idus);

        return "modificarcultivo";
    }

    @PostMapping("/guardarcultivo")
    public String guardar(Cultivo cultivo){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID USUARIO Save: "+idus);
        Usuario usu=new Usuario();
        usu.setId(idus);
        cultivo.setUsuario(usu);
        cultivoService.guardar(cultivo);
        return "redirect:/cultivo";
    }

    @GetMapping("/editarcultivo/{id}")//ya existe lo asocia
    public String editar(Cultivo cultivo, Model model){
        Integer idusuario= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID US EDIT CULTIVO: "+idusuario);
        cultivo=cultivoService.buscarCultivo(cultivo);
        model.addAttribute("cultivo",cultivo);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("productos",productoService.listarProducto());
        return "modificarcultivo";
    }

//    @GetMapping("/eliminarcultivo/{id}")
//    public String eliminar(Cultivo cultivo){
//        cultivoService.eliminar(cultivo);
//        return "redirect:/cultivo";
//    }

    @GetMapping("/eliminarcultivo/{id}")
    public String eliminarCultivo(Cultivo cultivo){
        cultivoService.eliminarCultivo(cultivo.getId());
        return "redirect:/cultivo";
    }

}
