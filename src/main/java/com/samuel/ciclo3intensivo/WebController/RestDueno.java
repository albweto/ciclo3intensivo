package com.samuel.ciclo3intensivo.WebController;

import com.samuel.ciclo3intensivo.Service.DuenoServiceImpl;
import com.samuel.ciclo3intensivo.models.Dueno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RestDueno {

    @Autowired
    private DuenoServiceImpl duenoService;


    @GetMapping("/alldueno")
    public ResponseEntity<List<Dueno>> getAllDueno(){
        try{
            List<Dueno> duenos = duenoService.listar();
            return new ResponseEntity<>(duenos, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dueno/{id}")
    public ResponseEntity<Dueno> getDuenoById(@PathVariable("id") int id){
        try{
            Optional<Dueno> dueno = duenoService.ListarById(id);
            return new ResponseEntity<>(dueno.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/dueno")
    public ResponseEntity<String> createDueno(@RequestBody Dueno dueno){
        String message = "";
        try{
            boolean _dueno = duenoService.registar(new Dueno(dueno.getNombrePropietario()));
            if (_dueno){
                message = "Creado con exito";
                return new ResponseEntity<>(message, HttpStatus.OK);
            }else{
                message = "ah Ocurrido un Error";
                return new ResponseEntity<>(message, HttpStatus.FAILED_DEPENDENCY);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dueno/{id}")
    public ResponseEntity<Dueno> updateDueno(@PathVariable("id") int id,@RequestBody Dueno dueno ){
        Optional<Dueno> duenoData = duenoService.ListarById(id);
        if(duenoData.isPresent()){
            Dueno _dueno = duenoData.get();
            _dueno.setNombrePropietario(dueno.getNombrePropietario());
            boolean data = duenoService.registar(_dueno);
            if (data){

                return new ResponseEntity<>(null, HttpStatus.OK);
            }else{

                return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("dueno/{id}")
    public ResponseEntity<HttpStatus> delteDueno(@PathVariable("id") int id){
        try{
            duenoService.eliminiar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
