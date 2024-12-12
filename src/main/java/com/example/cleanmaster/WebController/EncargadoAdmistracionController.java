package com.example.cleanmaster.WebController;

import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EncargadoAdmistracionController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("areaempleados/home/{id}/administrar")
    public ModelAndView administrarEmpleados() {
        ModelAndView modelAndView = new ModelAndView("./paginas/encargado.html");
        return modelAndView;
    }


    @GetMapping("/areaencargados/empleado/alta")
    public ModelAndView altaEmpleado() {
        ModelAndView modelAndView = new ModelAndView("./paginas/modificarempleado.html");
        modelAndView.addObject("nombre","");
        modelAndView.addObject("correo", "");
        modelAndView.addObject("apellidos", "");
        modelAndView.addObject("telefono", "");
        modelAndView.addObject("numss", "");
        modelAndView.addObject("iban", "");
        modelAndView.addObject("visible", true);
        return modelAndView;
    }

    @GetMapping("/areaencargados/empleado/baja/verempleado")
    public ModelAndView bajaEmpleado() {
        ModelAndView modelAndView = new ModelAndView("./paginas/buscarempleado.html");
        return modelAndView;
    }

    @GetMapping("/areaencargados/empleado/modificarHorario")
    public ModelAndView modificarHorario() {
        ModelAndView modelAndView = new ModelAndView("./paginas/modificarHorarios.html");
        return modelAndView;
    }
    @GetMapping("/areaencargados/empleado/baja/verempleado/{correo}")
    public ModelAndView altaEmpleado(@PathVariable("correo") String correo) {
        EmpleadoDTO empleado = empleadoService.existsByCorreo(correo);
        ModelAndView modelAndView = new ModelAndView("./paginas/modificarempleado.html");
        modelAndView.addObject("nombre", empleado.getNombre());
        modelAndView.addObject("correo", empleado.getCorreo());
        modelAndView.addObject("apellidos", empleado.getApellidos());
        modelAndView.addObject("telefono", empleado.getMovil());
        modelAndView.addObject("numss", empleado.getNumss());
        modelAndView.addObject("iban", empleado.getIban());
        modelAndView.addObject("visible", false);
        return modelAndView;
    }

}
