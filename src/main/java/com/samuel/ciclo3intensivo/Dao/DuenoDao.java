package com.samuel.ciclo3intensivo.Dao;

import com.samuel.ciclo3intensivo.models.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenoDao extends JpaRepository<Dueno,Integer> {
}
