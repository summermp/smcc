package com.pac.smcc.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    public Cultivo() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", insertable = true, updatable = true, nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy="parametrocultivo",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Parametros> parametros;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cultivoDispositivo")
    private List<Dispositivo> discultivo;
}
