package com.example.cleanmaster.WebController;


import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.Service.DireccionesService;
import com.example.cleanmaster.Service.TiposServiciosService;
import com.example.cleanmaster.models.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteHomeController {
    @Autowired
    private ClienteService clienteService;


    @GetMapping("/areaclientes/home/{id}")
    public ModelAndView homeCliente() {
        ModelAndView modelAndView = new ModelAndView("./paginas/home.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }


    @GetMapping("/areaclientes/home/{id}/veragenda")
    public ModelAndView verAgendaSemana() {
        ModelAndView modelAndView = new ModelAndView("./paginas/veragenda.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }

    @GetMapping("/areaclientes/home/{id}/verperfil")
    public ModelAndView verPerfil(@PathVariable("id") Integer id) {
        ClienteDTO x = clienteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("./paginas/perfil.html");
        modelAndView.addObject("escliente", "true");
        modelAndView.addObject("nombre", x.getNombre());
        modelAndView.addObject("email", x.getCorreo());
        modelAndView.addObject("telefono", x.getMovil());
        return modelAndView;
    }

    @GetMapping("/areaclientes/home/{id}/nuevareserva")
    public ModelAndView nuevaReserva() {
        ModelAndView modelAndView = new ModelAndView("./paginas/nuevasreservas.html");
        modelAndView.addObject("escliente", "true");
        return modelAndView;
    }

    @GetMapping("/areaclientes/home/{id}/nuevadireccion")
    public  ModelAndView nuevaDireccion(){
        ModelAndView modelAndView = new ModelAndView("./paginas/nuevadireccion.html");
        return modelAndView;
    }
    @GetMapping("/areaclientes/home/{id}/verdirecciones")
    public ModelAndView verDirecciones(){
        ModelAndView modelAndView = new ModelAndView("./paginas/verdirecciones.html");
        return modelAndView;
    }
    @GetMapping("/areaclientes/home/{id}/modificarperfil")
    public ModelAndView moidificarPerfil(@PathVariable("id") Integer id) {
        ClienteDTO x = clienteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("./paginas/modificarperfil.html");
        modelAndView.addObject("escliente", "true");
        modelAndView.addObject("nombre", x.getNombre());
        modelAndView.addObject("email", x.getCorreo());
        modelAndView.addObject("telefono", x.getMovil());
        return modelAndView;
    }

    @GetMapping("/areaclientes/home/{id}/verhistorial")
    public ModelAndView verHistorial(){
        ModelAndView modelAndView = new ModelAndView("./paginas/verhistorial.html");
        return modelAndView;
    }
    @GetMapping("/areaclientes/home/{id}/vermensajes")
    public ModelAndView verMensajes() {
        return new ModelAndView("./paginas/vermensajes.html");
    }


    @GetMapping("/areaclientes/home/{id}/nuevomensajes")
    public ModelAndView nuevoMensaje() {
        return new ModelAndView("./paginas/nuevomensajes.html");
    }

}
