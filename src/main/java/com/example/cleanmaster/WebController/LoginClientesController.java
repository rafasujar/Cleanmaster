package com.example.cleanmaster.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginClientesController {

    @GetMapping("/areaclientes/auth")
    public ModelAndView LoginEmpleados() {
        ModelAndView modelAndView = new ModelAndView("./paginas/auth.html");
        modelAndView.addObject("escliente", true);
        modelAndView.addObject("apiUrl", "/areaclientes/api/login");
        return modelAndView;
    }

    @GetMapping("/areaclientes/passwd")
    public ModelAndView RecuperarPasswd() {
        ModelAndView modelAndView = new ModelAndView("./paginas/resetpasswd.html");
        modelAndView.addObject("escliente", true);
        modelAndView.addObject("apiUrl", "/areacliente/api/resetpasswd");
        return modelAndView;
    }

    @GetMapping("/areaclientes/registro")
    public ModelAndView Registro() {
        ModelAndView modelAndView = new ModelAndView("./paginas/registro.html");
        return modelAndView;
    }
}
