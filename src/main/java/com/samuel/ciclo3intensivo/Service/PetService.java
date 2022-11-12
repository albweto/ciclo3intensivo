package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.models.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    public boolean registrar(Pet pet);
    public void eliminar(int idPet);
    public Optional<Pet> listarId(int idPet);
    public Optional<Pet> buscarId(int idPet);
    List<Pet> listar();
    List<Pet> buscarNombre(String namePet);
    List<Pet> buscarRaza(String nameRace);
    List<Pet> buscarPropietario(String nameDueno);
}
