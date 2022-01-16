package com.pac.smcc.service;
import com.pac.smcc.domain.Usuario;
import com.pac.smcc.dto.UsuarioDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> listarUsuario();

    void guardar(Usuario usuario);

    void eliminar(Usuario usuario);

    Usuario buscarUsuario(Usuario usuario);

    boolean existeUsuario(String nombreusuario);

    boolean existeEmail(String email);

    Usuario obtenerUsuario(String email);

    void actualizarclave(String clave, Integer idusuario);

    List<UsuarioDTO> obtenerUsuario(Integer idusuario);

}
