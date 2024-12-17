package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.models.dto.ClienteDTO;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class load {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/loadEmpleado")
    public ModelAndView load() {
        ModelAndView modelAndView = new ModelAndView("./paginas/load.html");
        EmpleadoDTO empleadoDTO2 = empleadoService.findById(2);
        String token = utilsCleanMaster.generateToken(true, empleadoDTO2.getId(), empleadoDTO2.getCorreo(), empleadoDTO2.getNombre() + " " + empleadoDTO2.getApellidos());
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @GetMapping("/loadEncargado")
    public ModelAndView loade() {
        ModelAndView modelAndView = new ModelAndView("./paginas/load.html");
        EmpleadoDTO empleadoDTO2 = empleadoService.findById(1);
        String token = utilsCleanMaster.generateToken(true, empleadoDTO2.getId(), empleadoDTO2.getCorreo(), empleadoDTO2.getNombre() + " " + empleadoDTO2.getApellidos());
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @GetMapping("/loadCliente")
    public ModelAndView load1() {
        ModelAndView modelAndView = new ModelAndView("./paginas/load.html");
        ClienteDTO clienteDTO = clienteService.findById(1);
        String token = utilsCleanMaster.generateToken(false, clienteDTO.getId(), clienteDTO.getCorreo(), clienteDTO.getNombre());
        modelAndView.addObject("token", token);
        return modelAndView;
    }
}
