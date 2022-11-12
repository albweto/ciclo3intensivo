package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.Dao.DuenoDao;
import com.samuel.ciclo3intensivo.models.Dueno;
import com.samuel.ciclo3intensivo.models.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DuenoServiceImpl implements DuenoService{

    @Autowired
    private DuenoDao duenoDao;

    @Override
    @Transactional
    public boolean registar(Dueno dueno) {
        Dueno objDueno = duenoDao.save(dueno);
        if (objDueno == null){
            return  false;
        }else {
            return true;
        }
    }

    @Override
    @Transactional
    public void eliminiar(int idPropietario) {
        duenoDao.deleteById(idPropietario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dueno> ListarById(int idPropietario) {
        return duenoDao.findById(idPropietario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dueno> listar() {
        return duenoDao.findAll();
    }
}
