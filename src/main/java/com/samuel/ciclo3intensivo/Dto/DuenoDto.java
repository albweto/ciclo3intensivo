package com.samuel.ciclo3intensivo.Dto;

public class DuenoDto {
    private  String nombrePropietario;

    public DuenoDto() {
    }

    public DuenoDto(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }
}
