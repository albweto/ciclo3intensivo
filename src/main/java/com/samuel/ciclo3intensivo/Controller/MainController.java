package com.samuel.ciclo3intensivo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("metaTitle" , "pagina de login");
        return "login";
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }
}
