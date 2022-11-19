package com.samuel.ciclo3intensivo.Controller;

import com.samuel.ciclo3intensivo.Service.DuenoServiceImpl;
import com.samuel.ciclo3intensivo.Service.PetServiceImpl;
import com.samuel.ciclo3intensivo.Service.RaceServiceImpl;
import com.samuel.ciclo3intensivo.models.Dueno;
import com.samuel.ciclo3intensivo.models.Pet;
import com.samuel.ciclo3intensivo.models.Race;
import org.apache.el.parser.ParseException;
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
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private DuenoServiceImpl  duenoService;
    @Autowired
    private PetServiceImpl petService;
    @Autowired
    private RaceServiceImpl raceService;



    @RequestMapping("/")
    public String irPaginaListadoMascotas(Map<String, Object> model) {
        model.put("listaMascotas", petService.listar());
        return "views/listPet";
    }

    @RequestMapping("/irRegistrar")
    public String irPaginaRegistrar(Model model) {

        model.addAttribute("race", new Race());
        model.addAttribute("dueno", new Dueno());
        model.addAttribute("pet", new Pet());

        model.addAttribute("listaRazas", raceService.listar());
        model.addAttribute("listaDuenos", duenoService.listar());

        return "views/pet";
    }

    @RequestMapping("/registrar")
    public String registrar(@ModelAttribute Pet objPet, BindingResult binRes, Model model)
            throws ParseException
    {
        if (binRes.hasErrors())
        {
            model.addAttribute("listaRazas", raceService.listar());
            model.addAttribute("listaDuenos", duenoService.listar());
            return "views/pet";
        }
        else {
            boolean flag = petService.registrar(objPet);
            if (flag)
                return "redirect:/pet/listar";
            else {
                model.addAttribute("mensaje", "Ocurrio un error");
                return "redirect:/pet/irRegistrar";
            }
        }
    }


    @RequestMapping("/modificar/{id}")
    public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
            throws ParseException
    {
        Optional<Pet> objPet = petService.buscarId(id);
        if (objPet == null) {
            objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
            return "redirect:/pet/listar";
        }
        else {
            model.addAttribute("listaRazas", raceService.listar());
            model.addAttribute("listaDuenos", duenoService.listar());

            if (objPet.isPresent())
                objPet.ifPresent(o -> model.addAttribute("pet", o));

            return "views/pet";
        }
    }

    @RequestMapping("/eliminar")
    public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
        try {
            if (id!=null && id>0) {
                petService.eliminar(id);
                model.put("listaMascotas", petService.listar());
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            model.put("mensaje","Ocurrio un roche");
            model.put("listaMascotas", petService.listar());

        }
        return "views/listPet";
    }

    @RequestMapping("/listar")
    public String listar(Map<String, Object> model) {
        model.put("listaMascotas", petService.listar());
        return "views/listPet";
    }

    @RequestMapping("/listarId")
    public String listarId(Map<String, Object> model, @ModelAttribute Pet pet)
            throws ParseException
    {
        petService.listarId(pet.getIdPet());
        return "views/listPet";
    }

    @RequestMapping("/irBuscar")
    public String irBuscar(Model model)
    {
        model.addAttribute("pet", new Pet());
        return "views/buscarPet";
    }

    @RequestMapping("/buscar")
    public String buscar(Map<String, Object> model, @ModelAttribute Pet pet)
            throws ParseException
    {
        List<Pet> listaMascotas;
        pet.setNombrePet(pet.getNombrePet());
        listaMascotas = petService.buscarNombre(pet.getNombrePet());
        if (listaMascotas.isEmpty()) {
            listaMascotas = petService.buscarPropietario(pet.getNombrePet());
        }
        if (listaMascotas.isEmpty()) {
            listaMascotas = petService.buscarRaza(pet.getNombrePet());
        }
        if (listaMascotas.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaMascotas", listaMascotas);
        return "views/buscarPet";
    }
}
