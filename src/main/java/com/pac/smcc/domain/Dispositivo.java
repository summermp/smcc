package com.pac.smcc.domain;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name ="dispositivo")
public class Dispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String estado;
    private String ip;

    @ManyToOne
    @JoinColumn(name = "cultivo_id",insertable = true, updatable = true, nullable = false)
    private Cultivo cultivoDispositivo;
}
