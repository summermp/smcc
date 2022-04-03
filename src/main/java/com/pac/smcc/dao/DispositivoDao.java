package com.pac.smcc.dao;

import com.pac.smcc.domain.Dispositivo;
import com.pac.smcc.dto.CultivoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DispositivoDao extends JpaRepository<Dispositivo, Integer> {
    @Transactional
    @Modifying
    @Query(value="update dispositivo d set d.cultivo_id=:idcultivo  where d.id=1",nativeQuery = true)
    void updateCultivoById(@Param("idcultivo") Integer idcultivo);

    @Transactional(readOnly = true)
    @Query(value="select * from dispositivo d where d.id=1",nativeQuery = true)
    Dispositivo getIdcultivo();
}
