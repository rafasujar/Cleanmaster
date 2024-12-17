package com.example.cleanmaster.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactoController {

    @GetMapping("/contactar")
    public ModelAndView Contacto() {
        return new ModelAndView("./paginas/contacto.html");
    }
}
