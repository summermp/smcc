package com.pac.smcc.web;
import com.pac.smcc.domain.Rol;
import com.pac.smcc.domain.Usuario;
import com.pac.smcc.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Controller
@Slf4j
public class UsuarioControlador {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession httpSession;


    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/usuarios")
    public String incio(Model model, @AuthenticationPrincipal User user) {
        Integer idusuario= (Integer) httpSession.getAttribute("idusuario");
        log.error("ID USUARIO: "+idusuario);
        var usuarios=usuarioService.listarUsuario();
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute( "usuarios",usuarios);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Usuario usuario, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario", datos_usuario);
        return "modificarusuario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, @RequestParam("foto") String fotoUsuario,
                          Errors errores, Model model) throws IOException {
        //GUARDA ID USUARIO AL INICIAR SESION
        if(httpSession.getAttribute("idusuario")!=null){
            Integer idusuario= (Integer) httpSession.getAttribute("idusuario");
            log.error("ID USUARIO: "+idusuario);
        }
        //EVALUA SI EXISTE EL MISMO USUARIO
        if(usuarioService.existeUsuario(usuario.getNombre()) && (usuario.getId()==null)){
            log.error("Si existe");
            model.addAttribute("error", "usuario existe!");
            log.error("usuario: "+usuario.getNombre());
            return "modificarusuario";
        }else if(errores.hasErrors()){
            return "modificarusuario";
        }else{
            String str = usuario.getNombre();
            String[] splited = str.split("\\s+");
            log.error("username: "+splited[0]);
            usuario.setNombreusuario(splited[0].toLowerCase());
            //CLAVES ANTES DE CREAR USUARIO
            if(usuario.getClave().length()<=22){
                    String codificado=bCryptPasswordEncoder.encode(usuario.getClave().split(",")[1]);
                    System.out.println("CLAVE CON: "+usuario.getClave());
                    System.out.println("CLAVE SIN: "+usuario.getClave().split(",")[1]);
                    System.out.println("CODI: "+codificado);
                    usuario.setClave(codificado);
                    log.error("Se cambio la clave");
            //CLAVES DESPUES DE CREAR USUARIO
            }else{
                    log.error("USUARIO CLAVE: "+usuario.getClave());
                    String mismaclave = usuario.getClave().substring(0, 60);
                    usuario.setClave(mismaclave);
                    log.error("Es la misma clave");
            }
            if(fotoUsuario.length()<10){
                usuario.setFoto("https://cdn.filestackcontent.com/e0uTGIlRSi6u6doxJPjA");
            }else{
                usuario.setFoto(fotoUsuario);
            }
            usuario.setFoto(fotoUsuario);
            log.error(usuario.getFoto());
            usuarioService.guardar(usuario);
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/editar/{id}")//ya existe lo asocia
    public String editar(Usuario usuario, Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        usuario=usuarioService.buscarUsuario(usuario);
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        model.addAttribute("usuario",usuario);
        return "modificarusuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Usuario usuario) throws IOException{
        usuario=usuarioService.buscarUsuario(usuario);
        usuarioService.eliminar(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/recuperarcuenta")
    public String recuperarcuenta(@RequestParam("clave") String clave, @RequestParam("idusu") String idusuario,
                                  Model model) {
            log.error("ID USUARIO: "+idusuario);
            String msg="";
            String password = bCryptPasswordEncoder.encode(clave);
            usuarioService.actualizarclave(password,Integer.parseInt(idusuario));
            model.addAttribute("actualizado",msg);
            return "login";
    }

    @GetMapping("/verificarcorreo")
    public String verificarcorreo(@RequestParam(required=false,name="correo") String correo, Model model){
        String verificado="";
        String hideform=null;
        if(correo!=null){
            if(usuarioService.existeEmail(correo)){
                Usuario uss=new Usuario();
                verificado="ok";
                uss=usuarioService.obtenerUsuario(correo);
                Integer idusuario=uss.getId();
                log.error("ID USUARIO: "+idusuario);
                model.addAttribute("verificado",verificado);
                model.addAttribute("idusuario",idusuario);
                model.addAttribute("hideform",hideform);
                return "olvideclave";
            }else{
                model.addAttribute("noexiste","noexiste");
                return "redirect:/olvideclave";
            }
        }else{
            return "olvideclave";
        }
    }

    @RequestMapping(value={"/inicio","/"})
    public String inicio(){
        return "inicio";
    }

    @GetMapping("/contacto")
    public String contacto(){
        return "contacto";
    }

    @GetMapping("/acerca")
    public String acerca(){
        return "acerca";
    }

    @GetMapping("/ayuda")
    public String ayuda(Model model){
        Integer idus= (Integer) httpSession.getAttribute("idusuario");
        var datos_usuario=usuarioService.obtenerUsuario(idus);
        model.addAttribute("datos_usuario",datos_usuario);
        return "ayuda";
    }

    @GetMapping("/validacion")
    public String validacion(){
        return "validarcodigo";
    }

    @GetMapping("/olvideclave")
    public String olvideclave(Model model){
        String hideform="habilitado";
        model.addAttribute("hideform",hideform);
        return "olvideclave";
    }

    @GetMapping("/redes")
    public String redes(Model model) {
        var redes = usuarioService.listarUsuario();
        model.addAttribute("redes", redes);
        return "redes";
    }

        @PostMapping("/actualizar_imagen")
        public String actualizar_redsocial(@RequestParam("clave") String clave, @RequestParam("idusu") String idusuario,
                Model model) {
            log.error("ID USUARIO: "+idusuario);
            String msg="";
            String password = bCryptPasswordEncoder.encode(clave);
            usuarioService.actualizarclave(password,Integer.parseInt(idusuario));
            model.addAttribute("actualizado",msg);
            return "login";
        }

    }

