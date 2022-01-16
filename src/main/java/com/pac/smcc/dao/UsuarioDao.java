package com.pac.smcc.dao;
import com.pac.smcc.domain.Usuario;
import com.pac.smcc.dto.CultivoDTO;
import com.pac.smcc.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//jparepo tienes mas herramientas
public interface UsuarioDao extends JpaRepository<Usuario,Integer> {
    Usuario findByNombreusuario(String nombre);

    @Query("select count(u) = 1 from Usuario u where nombre = ?1")
    public boolean findExistByNombre(String nombre);

    @Query("select count(u) = 1 from Usuario u where email = ?1")
    public boolean findByEmail(String correo);

    @Query(value="select * from Usuario u where email = ?1",nativeQuery = true)
    public Usuario getByEmail(String correo);

    @Transactional(readOnly = true)
    @Query(value="select u.id as id, u.nombre as nombre,u.Foto as foto, u.email as email from Usuario u where u.id =:idusuario",nativeQuery = true)
    public List<UsuarioDTO> getUsuario(@Param("idusuario") Integer idusuario);

    @Modifying
    @Query(value="update Usuario u set clave =:clave where u.id =:idusuario",nativeQuery = true)
    public void updateClaveUsuario(String clave, Integer idusuario);
}
