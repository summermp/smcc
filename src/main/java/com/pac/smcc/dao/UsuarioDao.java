package com.pac.smcc.dao;
import com.pac.smcc.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//jparepo tienes mas herramientas
public interface UsuarioDao extends JpaRepository<Usuario,Integer> {
    Usuario findByNombre(String nombre);

    @Query("select count(u) = 1 from Usuario u where nombre = ?1")
    public boolean findExistByNombre(String nombre);

    @Query("select count(u) = 1 from Usuario u where email = ?1")
    public boolean findByEmail(String correo);

    @Query(value="select * from Usuario u where email = ?1",nativeQuery = true)
    public Usuario getByEmail(String correo);

    @Modifying
    @Query(value="update Usuario u set clave =:clave where u.id =:idusuario",nativeQuery = true)
    public void updateClaveUsuario(String clave, Integer idusuario);
}
