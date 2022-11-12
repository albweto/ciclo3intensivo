package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.Dao.RaceDao;
import com.samuel.ciclo3intensivo.models.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService{

    @Autowired
    private RaceDao raceDao;

    @Override
    @Transactional
    public boolean registar(Race race) {
        Race objrace = raceDao.save(race);
        if (objrace == null){
            return false;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void eliminiar(int idRace) {
            raceDao.deleteById(idRace);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Race> ListarById(int idRace) {
        return raceDao.findById(idRace);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Race> listar() {
        return raceDao.findAll();
    }

    @Override
    public List<Race> buscarByNombre(String nombre) {
        return raceDao.findByNombreRace(nombre);
    }
}
