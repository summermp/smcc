package com.pac.smcc.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id",insertable = true, updatable = true, nullable = false)
    private Categoria categoriaproducto;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="producto", cascade = CascadeType.ALL)
    private List<Cultivo> cultivo;
}
