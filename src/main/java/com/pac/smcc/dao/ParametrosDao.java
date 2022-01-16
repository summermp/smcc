package com.pac.smcc.dao;
import com.pac.smcc.domain.Parametros;
import com.pac.smcc.dto.ParametroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParametrosDao extends JpaRepository<Parametros, Integer> {
    @Query(value="select * from parametro where id_cultivo=:idcultivo\n" +
            "and fechahora between :fecha1 and DATE_ADD(:fecha2,INTERVAL 1 DAY)", nativeQuery = true)
    List<Parametros> findAllByFechahora(@Param("fecha1") String fecha1, @Param("fecha2") String fecha2, @Param("idcultivo")
    Integer idcultivo);

    @Query(value="select p.id as id,p.ha as ha,p.hs as hs,p.ta as ta,p.ts as ts,\n" +
            "p.nitrogeno as nitrogeno,p.potasio as potasio,p.fosforo as fosforo,\n"+
            "p.ph as ph,p.co2 as co2,p.ce as ce,p.uv as uv,pro.nombre as nombrecultivo,p.fechahora as fechahora\n"+
            "from parametro p\n" +
            "inner join cultivo c on p.id_cultivo = c.Id\n" +
            "inner join usuario u on c.usuario_id = u.Id\n" +
            "inner join producto pro on c.producto_id = pro.Id\n" +
            "where u.Id=:idusuario and\n" +
            "c.Id=(select id from cultivo where usuario_id=:idusuario order by id desc limit 1)", nativeQuery=true)
    List<ParametroDTO> findAllByCultivo(@Param("idusuario") Integer idusuario);

    @Query(value="select p.id as id,p.ha as ha,p.hs as hs,p.ta as ta,p.ts as ts,\n" +
            "p.nitrogeno as nitrogeno,p.potasio as potasio,p.fosforo as fosforo,\n"+
            "p.ph as ph,p.co2 as co2,p.ce as ce,p.uv as uv,pro.nombre as nombrecultivo,p.fechahora as fechahora\n"+
            "from parametro p\n" +
            "inner join cultivo c on p.id_cultivo = c.Id\n" +
            "inner join usuario u on c.usuario_id = u.Id\n" +
            "inner join producto pro on c.producto_id = pro.Id\n" +
            "where u.Id=:idusuario and\n" +
            "c.Id=(select id from cultivo where usuario_id=:idusuario order by id desc limit 1)\n"+
            "order by p.id desc limit 1;", nativeQuery=true)
    List<ParametroDTO> findParametros(@Param("idusuario") Integer idusuario);
}
