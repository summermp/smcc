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
        Usuario us= usuarioDao.findByNombre(usuario);
        if(usuario == null){
            throw new UsernameNotFoundException(usuario);
        }
        if(us.getId() != null){
            Integer idusuario=us.getId();
            httpSession.setAttribute("idusuario",idusuario);
        }

        var roles=new ArrayList<GrantedAuthority>();
        log.error("nose que pasa");
        for(Rol rol: us.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getTipo()));
            log.error("rol: "+rol.getTipo());
        }
        log.error("nombre: "+us.getNombre()+" clave: "+us.getClave()+" "+roles);
        return new User(us.getNombre(),us.getClave(),roles);
    }
}
