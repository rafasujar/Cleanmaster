package com.example.cleanmaster.ServicioEmpleados.RestApi;

import com.example.cleanmaster.ServicioEmpleados.Service.EmpleadoService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
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


            // sigue jugando con el optional para que te permita no fallar si no encuentra el empleado

           EmpleadoDTO empleadoDTO = empleadoService.logearEmpleado(empleadoDTO2.getCorreo(), empleadoDTO2.getPassword());
           System.out.println(empleadoDTO.toString());
            if (empleadoDTO.equals(null)) {
                return ResponseEntity.ok("{" +
                   "correo:'" + empleadoDTO.getCorreo() + "'," +
                   "idEmpleado:'" + empleadoDTO.getId() + "'," +
                   "nombre:'" + empleadoDTO.getNombre() + "'," +
                   "apellido:'" + empleadoDTO.getApellidos() +
                   "}");

        } else {


            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado no fue encontrado");
        }
    }

}
