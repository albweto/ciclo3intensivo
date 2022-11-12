package com.samuel.ciclo3intensivo.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Race")
@Data
public class Race implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRace;

    @Column(name = "nombreRaza", length = 60, nullable=false)
    private String nombreRace;
}
