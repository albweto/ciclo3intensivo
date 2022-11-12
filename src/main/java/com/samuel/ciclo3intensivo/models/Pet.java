package com.samuel.ciclo3intensivo.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Mascota")
@Data
public class Pet  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private int idPet;

    @Column(name = "nombreMascota")
    private String nombrePet;

    @Temporal(TemporalType.DATE)
    @Column(name="fechaNacimiento")
    @DateTimeFormat(pattern="yyy-MM-dd")
    private Date birthDatePet;

    @ManyToOne
    @JoinColumn(name="id_propietario", nullable=false)
    private Dueno dueno;

    @ManyToOne
    @JoinColumn(name="idRace", nullable=false)
    private Race race;
}
