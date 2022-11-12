package com.samuel.ciclo3intensivo.Dao;

import com.samuel.ciclo3intensivo.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetDao extends JpaRepository<Pet,Integer> {

     List<Pet> findByNombrePet(String nombre);
    @Query("from Pet p where p.race.nombreRace like %:nameRace%")
    List<Pet> buscarRaza(@Param("nameRace") String nameRace);

    @Query("from Pet p where p.dueno.nombrePropietario like %:nameDueno%")
    List<Pet> buscarPropietario(@Param("nameDueno") String nameDueno);

    List<Pet> findByBirthDatePet(Date birthDatePet);
}
