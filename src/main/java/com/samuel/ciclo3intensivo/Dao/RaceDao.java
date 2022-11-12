package com.samuel.ciclo3intensivo.Dao;

import com.samuel.ciclo3intensivo.models.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceDao extends JpaRepository<Race,Integer> {
    List<Race> findByNombreRace(String nombreRace);
}
