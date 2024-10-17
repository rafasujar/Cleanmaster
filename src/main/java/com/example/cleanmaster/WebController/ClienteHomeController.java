package com.example.cleanmaster.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteHomeController {



    @GetMapping("/areaclientes/home/{id}")
    public ModelAndView homeCliente(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("./paginas/home.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }
    




}
