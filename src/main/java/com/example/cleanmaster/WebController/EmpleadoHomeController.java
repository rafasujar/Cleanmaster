package com.example.cleanmaster.WebController;

import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.EncargadoService;
import com.example.cleanmaster.Service.TiposServiciosService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpleadoHomeController {
    @Autowired
    private EncargadoService encargadoService;
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    TiposServiciosService tiposServiciosService;

    @GetMapping("/areaempleados/home/{id}")
    public ModelAndView homeEmpleado(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("./paginas/home.html");
        modelAndView.addObject("escliente", "false");
        modelAndView.addObject("esencargado", encargadoService.esEncargado(id));
        return modelAndView;
    }

    @GetMapping("/areaempleados/home/{id}/veragenda")
    public ModelAndView verAgendaSemana() {
        ModelAndView modelAndView = new ModelAndView("./paginas/veragenda.html");
        modelAndView.addObject("escliente", "false");
        return modelAndView;
    }

    @GetMapping("/areaempleados/home/{id}/verperfil")
    public ModelAndView verPerfil(@PathVariable("id") Integer id) {
        EmpleadoDTO x = empleadoService.findById(id);
        ModelAndView modelAndView = new ModelAndView("./paginas/perfil.html");
        modelAndView.addObject("escliente", "false");
        modelAndView.addObject("nombre", x.getNombre());
        modelAndView.addObject("apellidos", x.getApellidos());
        modelAndView.addObject("email", x.getCorreo());
        modelAndView.addObject("telefono", x.getMovil());
        modelAndView.addObject("tipos", tiposServiciosService.getEmpleadoServicos(id).stream().map(r->r.getNombre()).toList());
        return modelAndView;
    }

    @GetMapping("/areaempleados/home/{id}/modificarperfil")
    public ModelAndView modificarPerfil(@PathVariable("id") Integer id) {
        EmpleadoDTO x = empleadoService.findById(id);
        ModelAndView modelAndView = new ModelAndView("./paginas/modificarperfil.html");
        modelAndView.addObject("escliente", "false");
        modelAndView.addObject("nombre", x.getNombre());
        modelAndView.addObject("apellidos", x.getApellidos());
        modelAndView.addObject("email", x.getCorreo());
        modelAndView.addObject("telefono", x.getMovil());
        modelAndView.addObject("tipos", tiposServiciosService.getEmpleadoServicos(id).stream().map(r->r.getNombre()).toList());
        return modelAndView;
    }

    @GetMapping("/areaempleados/home/{id}/vermensajes")
    public ModelAndView verMensajes() {
        return new ModelAndView("./paginas/vermensajes.html");
    }

    @GetMapping("/areaempleados/home/1/nuevomensajes")
    public ModelAndView nuevoMensaje() {
        return new ModelAndView("./paginas/nuevomensajes.html");
    }

}
