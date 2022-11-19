package com.samuel.ciclo3intensivo.Controller;

import com.samuel.ciclo3intensivo.Service.RaceServiceImpl;
import com.samuel.ciclo3intensivo.models.Race;
import ognl.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/race")
public class RaceController {

    @Autowired
    private RaceServiceImpl raceService;



    @RequestMapping("/")
    public String irPaginaListadoRazas(Map<String, Object> model) {
        model.put("listaRazas", raceService.listar());
        return "views/listRace";
    }

    @RequestMapping("/irRegistrar")
    public String irPaginaRegistrar(Model model) {
        model.addAttribute("race", new Race());
        return "views/race";

    }

    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Race objRace, BindingResult binRes, Model model)
            throws ParseException
    {
        if (binRes.hasErrors())
            return "views/race";
        else {
            boolean flag = raceService.registar(objRace);

            if (flag)
                return "redirect:/race/listar";
            else {
                model.addAttribute("mensaje", "Ocurrio un error");
                return "redirect:/race/irRegistrar";

            }
        }
    }

    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
            throws ParseException
    {
        Optional<Race> objRace = raceService.ListarById(id);
        if (objRace == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/race/listar";
        }
        else {
            model.addAttribute("race", objRace);
            return "views/race";
        }
    }

    @RequestMapping("/eliminar")
    public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
        try {
            if (id!=null && id>0) {
                raceService.eliminiar(id);
                model.put("listaRazas", raceService.listar());
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            model.put("mensaje","Ocurrio un roche");
            model.put("listaRazas", raceService.listar());
        }
        return "views/listRace";

    }

    @RequestMapping("/listar")
    public String listar(Map<String, Object> model) {
        model.put("listaRazas", raceService.listar());
        return "views/listRace";
    }

    @RequestMapping("/irBuscar")
    public String irBuscar(Model model)
    {
        model.addAttribute("race", new Race());
        return "views/buscar2";
    }

    @RequestMapping("/buscar2")
    public String buscar(Map<String, Object> model, @ModelAttribute Race race)
            throws ParseException
    {
        List<Race> listaRazas;
        race.setNombreRace(race.getNombreRace());
        listaRazas = raceService.buscarByNombre(race.getNombreRace());
        if (listaRazas.isEmpty()) {
            listaRazas = raceService.buscarByNombre(race.getNombreRace());
        }
        if (listaRazas.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaRazas", listaRazas);
        return "views/buscar2";
    }
}
