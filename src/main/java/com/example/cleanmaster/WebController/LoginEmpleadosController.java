package com.example.cleanmaster.WebController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginEmpleadosController {

@GetMapping("/areaempleados/auth")
    public ModelAndView LoginEmpleados(){
        return new ModelAndView("./paginas/areaempleadosauth.html");
    }

}
