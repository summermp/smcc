package com.pac.smcc.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="cultivo")
public class Cultivo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String fechasiembra;
    private float medida;
    private String ubicacion;

//    public Cultivo() {
//    }
//
//    public Cultivo(Integer id, String nombre) {
//        this.id = id;
//        this.fechasiembra = nombre;
//    }
//
//    public Cultivo(Integer id, String fechasiembra, float medida, String ubicacion, Producto producto, Usuario usuario, List<Parametros> parametros) {
//        this.id = id;
//        this.fechasiembra = fechasiembra;
//        this.medida = medida;
//        this.ubicacion = ubicacion;
//        this.producto = producto;
//        this.usuario = usuario;
//        this.parametros = parametros;
//    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id",insertable = true, updatable = true, nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id",insertable = true, updatable = true, nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy="parametrocultivo",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Parametros> parametros;
}
