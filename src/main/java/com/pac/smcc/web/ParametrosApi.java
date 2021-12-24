package com.pac.smcc.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pac.smcc.domain.Parametros;
import com.pac.smcc.service.ParametrosService;

@RestController
@RequestMapping("/api")
public class ParametrosApi {

    @Autowired
    private ParametrosService parametrosService;

    @PostMapping(value="/registrar")
    public Parametros registrarParametro(@RequestBody Parametros parametros){
        String fechahora = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        parametros.setFechahora(fechahora);
        return parametrosService.guardar(parametros);
    }
}
