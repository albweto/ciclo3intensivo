package com.samuel.ciclo3intensivo.Controller;

import com.samuel.ciclo3intensivo.Dto.UserRegistrationDto;
import com.samuel.ciclo3intensivo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl usuarioService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("metaTitle","Formulario de Registro");
        return "registration";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }



    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto ) {
        System.out.println(registrationDto);
        usuarioService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
