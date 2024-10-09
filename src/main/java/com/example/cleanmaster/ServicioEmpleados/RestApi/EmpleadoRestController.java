package com.example.cleanmaster.ServicioEmpleados.RestApi;

import com.example.cleanmaster.ServicioEmpleados.Service.EmpleadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class EmpleadoRestController {

    private EmpleadoService empleadoService;


    @PostMapping("/AreaEmpleado/api/login")
    public HttpResponse<String> loginEmpleado(@RequestBody String usuario ){
      return null;
    }

}
