package com.samuel.ciclo3intensivo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotEmpty
    private String nombre;



    public Role() {

    }

    public Role(String nombre) {
        super();
        this.nombre = nombre;
    }
}
