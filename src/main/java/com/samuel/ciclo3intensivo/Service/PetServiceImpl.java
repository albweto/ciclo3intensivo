package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.Dao.PetDao;
import com.samuel.ciclo3intensivo.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetDao petDao;

    @Override
    public boolean registrar(Pet pet) {
        Pet objPet = petDao.save(pet);
        if (objPet == null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void eliminar(int idPet) {
            petDao.deleteById(idPet);
    }

    @Override
    public Optional<Pet> listarId(int idPet) {
        return petDao.findById(idPet);
    }

    @Override
    public Optional<Pet> buscarId(int idPet) {
        return petDao.findById(idPet);
    }

    @Override
    public List<Pet> listar() {
        return petDao.findAll();
    }

    @Override
    public List<Pet> buscarNombre(String namePet) {
        return petDao.findByNombrePet(namePet);
    }

    @Override
    public List<Pet> buscarRaza(String nameRace) {
        return petDao.buscarRaza(nameRace);
    }

    @Override
    public List<Pet> buscarPropietario(String nameDueno) {
        return petDao.buscarPropietario(nameDueno);
    }
}
