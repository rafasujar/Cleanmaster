package com.example.cleanmaster.WebController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginEmpleadosController {

@GetMapping("/areaempleados/auth")
    public ModelAndView LoginEmpleados(){
        ModelAndView modelAndView =  new ModelAndView("./paginas/auth.html");
        modelAndView.addObject("escliente", false);
        modelAndView.addObject("apiUrl" , "/AreaEmpleado/api/login");
        return modelAndView;
    }



@GetMapping("/areaempleados/passwd")
    public ModelAndView RecuperarPasswd(){
    ModelAndView modelAndView =  new ModelAndView("./paginas/resetpasswd.html");
    modelAndView.addObject("escliente", false);
    modelAndView.addObject("apiUrl" , "/AreaEmpleado/api/resetpasswd");
    return modelAndView;
}
}
