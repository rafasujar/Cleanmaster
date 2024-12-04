package com.example.cleanmaster.WebController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteHomeController {
    @GetMapping("/areaclientes/home/{id}")
    public ModelAndView homeCliente() {
        ModelAndView modelAndView = new ModelAndView("./paginas/home.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }


    @GetMapping("/areaclientes/home/{id}/veragenda")
    public ModelAndView verAgendaSemana() {
        ModelAndView modelAndView = new ModelAndView("./paginas/agenda.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }




}
