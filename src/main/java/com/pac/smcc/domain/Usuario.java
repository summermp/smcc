package com.pac.smcc.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@DynamicUpdate
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String fechanacimiento;

    @NotEmpty
    private String celular;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String direccion;

    private String foto;

    @NotEmpty
    private String clave;

    private String nombreusuario;

    @Basic(optional = false)
    @Column(name = "ultimasesion", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimasesion;
//,cascade=CascadeType.ALL
    @OneToMany(mappedBy="usuario",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Cultivo> cultivos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_id")
    private List<Rol> roles;
}