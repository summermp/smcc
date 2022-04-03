package com.pac.smcc.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pac.smcc.domain.Cultivo;
import com.pac.smcc.domain.Dispositivo;
import com.pac.smcc.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pac.smcc.domain.Parametros;
import com.pac.smcc.service.ParametrosService;

@RestController
@RequestMapping("/api")
public class ParametrosApi {

    @Autowired
    private ParametrosService parametrosService;

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping(value="/registrar_parametros")
    public Parametros registrarParametro(@RequestBody Parametros parametros){
        String fechahora = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        Cultivo micultivo=new Cultivo();
        Dispositivo dispositivo =dispositivoService.obtenerIdcultivo();

        micultivo.setId(dispositivo.getCultivoDispositivo().getId());
        parametros.setParametrocultivo(micultivo);
        parametros.setFechahora(fechahora);
        return parametrosService.guardar(parametros);
    }
}
