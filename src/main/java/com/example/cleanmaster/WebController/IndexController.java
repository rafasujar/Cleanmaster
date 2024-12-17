package com.example.cleanmaster.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    @GetMapping("/")
    public ModelAndView Index() {
        return new ModelAndView("index.html");
    }

    @GetMapping("/inicio")
    public ModelAndView Inicio() {
        return Index();
    }

}
