package com.example.cleanmaster.ServicioEmpleados.RestApi;

import com.example.cleanmaster.ServicioEmpleados.Service.EmpleadoService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/AreaEmpleado/api/login")
    public ResponseEntity loginEmpleado(@RequestBody EmpleadoDTO empleadoDTO2) {
            System.out.println("EmpleadoDTO: " + empleadoDTO2.toString());
            
            if (empleadoDTO2.getCorreo().isEmpty() && empleadoDTO2.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El empleadodto2.getCorreo o la contraseña no pueden estar vacios");
            }

            if (!empleadoDTO2.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El empleadodto2.getCorreo no cumple con los requisitos de formato");
            }

            if (!empleadoDTO2.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La contraseña no cumple con los requisitos de seguridad");
            }


        try {
           EmpleadoDTO empleadoDTO = empleadoService.logearEmpleado(empleadoDTO2.getCorreo(), empleadoDTO2.getPassword());
            System.out.println("EmpleadoDTO: " + empleadoDTO.toString());
            if (empleadoDTO != null) {
                return ResponseEntity.ok(empleadoDTO);
            }
        } catch (NullPointerException e ) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado no fue encontrado");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor");

    }

}
