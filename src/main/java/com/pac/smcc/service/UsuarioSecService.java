package com.pac.smcc.service;

import com.pac.smcc.dao.UsuarioDao;
import com.pac.smcc.domain.Rol;
import com.pac.smcc.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioSecService implements UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession httpSession;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario us  = usuarioDao.findByNombreusuario(usuario);
        String nombre="", clave="";
        var roles=new ArrayList<GrantedAuthority>();
        if(us == null){
            nombre="none";
            clave="none";
            log.error(" No hay usuario ");
        }else{
            if(us.getId() != null){
                Integer idusuario=us.getId();
                httpSession.setAttribute("idusuario",idusuario);
                nombre=us.getNombreusuario();
                clave=us.getClave();
            }
            for(Rol rol: us.getRoles()){
                roles.add(new SimpleGrantedAuthority(rol.getTipo()));
                log.error("rol: "+rol.getTipo());
            }
            log.error("nombre: "+us.getNombreusuario()+" clave: "+us.getClave());

            BCryptPasswordEncoder bcp=new BCryptPasswordEncoder();
            boolean isPasswordMatch = bcp.matches("asd", us.getClave());
            log.error(" Coincide: "+isPasswordMatch);
        }
        return new User(nombre,clave,roles);
//        return new User(us.getNombre(),us.getClave(),roles);

    }

}
