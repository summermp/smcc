package com.pac.smcc.service;
import com.pac.smcc.domain.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UsuarioService {
    public List<Usuario> listarUsuario();

    public void guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario buscarUsuario(Usuario usuario);

    public boolean existeUsuario(String nombreusuario);

    public boolean existeEmail(String email);

    public Usuario obtenerUsuario(String email);

    public void actualizarclave(String clave, Integer idusuario);

}
