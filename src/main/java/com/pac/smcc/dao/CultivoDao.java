package com.pac.smcc.dao;
import java.util.List;

import com.pac.smcc.domain.Usuario;
import com.pac.smcc.dto.CultivoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pac.smcc.domain.Cultivo;

public interface CultivoDao extends JpaRepository<Cultivo,Integer> {
    @Transactional
    @Modifying
    @Query(value="delete from cultivo c where c.id=:idcultivo",nativeQuery = true)
    void deleteCultivoById(@Param("idcultivo") Integer idcultivo);


    @Transactional(readOnly = true)
    @Query(value="select c.id as id,p.nombre as nombre, c.fechasiembra as fechasiembra, c.medida as medida,\n" +
            "c.ubicacion as ubicacion\n"+
            "from cultivo c\n" +
            "inner join producto p\n" +
            "on c.producto_id = p.id\n" +
            "where c.usuario_id =:idusuario",nativeQuery = true)
    List<CultivoDTO> findAllByUsuario(@Param("idusuario") Integer idusuario);

    @Query(value="select * from usuario u where email = ?1",nativeQuery = true)
    public Integer getCultivo();
}
