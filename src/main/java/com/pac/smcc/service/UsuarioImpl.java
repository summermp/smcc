package com.pac.smcc.service;
import com.pac.smcc.dao.UsuarioDao;
import com.pac.smcc.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioImpl implements UsuarioService{
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuario() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getId()).orElse(null);
    }

    @Override
    public boolean existeUsuario(String nombreusuario) {
        return usuarioDao.findExistByNombre(nombreusuario);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    public Usuario obtenerUsuario(String email) {
        return usuarioDao.getByEmail(email);
    }

    @Override
    @Transactional
    public void actualizarclave(String clave, Integer idusuario) {
        usuarioDao.updateClaveUsuario(clave,idusuario);
    }
}
