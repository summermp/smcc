package com.pac.smcc.domain;
import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name ="parametro")
@Data
public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private float hs;//humedad suelo
    private float ha;//humedad ambiente
    private float ta;//tempera
    private float ts;
    private float ph;
    private float uv;
    private float co2;
//    private int npk;

//    @Basic(optional = false)
//    @Column(name = "fechahora", insertable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date fechahora;

    private String fechahora;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cultivo",insertable = true, updatable = true, nullable = false)
    private Cultivo parametrocultivo;
}

