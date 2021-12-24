package com.pac.smcc.dao;
import com.pac.smcc.domain.Parametros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParametrosDao extends JpaRepository<Parametros, Integer> {
    @Query(value="select * from parametro where id_cultivo=:idcultivo\n" +
            "and fechahora between :fecha1 and DATE_ADD(:fecha2,INTERVAL 1 DAY)", nativeQuery = true)
    List<Parametros> findAllByFechahora(@Param("fecha1") String fecha1, @Param("fecha2") String fecha2, @Param("idcultivo")
    Integer idcultivo);

    @Query(value="select p.id,p.fechahora,p.ha,p.hs,p.co2,p.ph,p.ta,p.ts,p.uv,p.id_cultivo\n" +
            "from parametro p\n" +
            "inner join cultivo c on p.id_cultivo = c.Id\n" +
            "inner join usuario u on c.usuario_id = u.Id\n" +
            "where u.Id=:idusuario and\n" +
            "c.Id=(select id from cultivo where usuario_id=:idusuario order by id desc limit 1)", nativeQuery=true)
    List<Parametros> findAllByCultivo(@Param("idusuario") Integer idusuario);

}
