package com.pac.smcc.domain;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name ="categoria")
@Data
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
//
//    @OneToMany(mappedBy="categoria",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Cultivo> cultivos;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="categoriaproducto",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Producto> productos;
}