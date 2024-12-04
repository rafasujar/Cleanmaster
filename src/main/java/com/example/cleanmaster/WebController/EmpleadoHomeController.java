package com.example.cleanmaster.WebController;

import com.example.cleanmaster.Service.EncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpleadoHomeController {
    @Autowired
    private EncargadoService encargadoService;


    @GetMapping("/areaempleados/home/{id}")
    public ModelAndView homeEmpleado(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("./paginas/home.html");
        modelAndView.addObject("escliente", "false");
        modelAndView.addObject("esencargado", encargadoService.esEncargado(id));
        return modelAndView;
    }

    @GetMapping("/areaempleados/home/{id}/veragenda")
    public ModelAndView verAgendaSemana() {
        ModelAndView modelAndView = new ModelAndView("./paginas/agenda.html");
        modelAndView.addObject("escliente", "false");
        return modelAndView;
    }



}
