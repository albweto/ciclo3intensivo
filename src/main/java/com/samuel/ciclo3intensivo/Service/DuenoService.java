package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.models.Dueno;


import java.util.List;
import java.util.Optional;

public interface DuenoService {
    public boolean registar(Dueno dueno);
    public void eliminiar(int idPropietario);
    public Optional<Dueno> ListarById(int idPropietario);
    List<Dueno> listar();
}
