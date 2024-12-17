package com.example.cleanmaster.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TerminosController {

    @GetMapping("/terminos")
    public ModelAndView Terminos() {
        return new ModelAndView("./paginas/terminos.html");
    }

}
