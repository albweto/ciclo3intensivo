package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.models.Race;

import java.util.List;
import java.util.Optional;

public interface RaceService {
    public boolean registar(Race race);
    public void eliminiar(int idRace);
    public Optional<Race> ListarById(int idRace);
    List<Race> listar();
    List<Race> buscarByNombre(String nombre);
}
