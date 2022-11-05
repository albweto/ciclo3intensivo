package com.samuel.ciclo3intensivo.Controller;

import com.samuel.ciclo3intensivo.Dto.UserRegistrationDto;
import com.samuel.ciclo3intensivo.Service.UserService;
import com.samuel.ciclo3intensivo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl usuarioService;

    @GetMapping
    public String showRegistrationForm() {
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
