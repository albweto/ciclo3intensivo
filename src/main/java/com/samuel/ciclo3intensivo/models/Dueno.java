package com.samuel.ciclo3intensivo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name = "propietario")
public class Dueno  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private int idPropietario;

    @Column(name = "nombre_propeitario",nullable = false)
    @NotEmpty
    private  String nombrePropietario;
}
