package com.example.cleanmaster.ServicioEmpleados.RestApi;

import com.example.cleanmaster.ServicioEmpleados.Service.EmpleadoService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

           Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.logearEmpleado(empleadoDTO2.getCorreo(), empleadoDTO2.getPassword()));
            if (empleadoDTO.isPresent()) {
                empleadoDTO2 = empleadoDTO.get();
                System.out.println(empleadoDTO2.toString());
                /*
                                return ResponseEntity.ok("{" +
                   "correo:'" + empleadoDTO2.getCorreo() + "'," +
                   "idEmpleado:'" + empleadoDTO2.getId() + "'," +
                   "nombre:'" + empleadoDTO2.getNombre() + "'," +
                   "apellido:'" + empleadoDTO2.getApellidos() +
                   "}");
                 */
                return ResponseEntity.ok(STR."{correo:'\{empleadoDTO2.getCorreo()}',idEmpleado:'\{empleadoDTO2.getId()}',nombre:'\{empleadoDTO2.getNombre()}',apellido:'\{empleadoDTO2.getApellidos()}}");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado no fue encontrado");
        }
    }

    @PostMapping("/AreaEmpleado/api/addEmpleado")
    public ResponseEntity addEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        if (empleadoDTO.getCorreo().isEmpty() && empleadoDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El empleado.getCorreo o la contraseña no pueden estar vacios");
        }

        if (!empleadoDTO.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El empleado.getCorreo no cumple con los requisitos de formato");
        }

        if (!empleadoDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La contraseña no cumple con los requisitos de seguridad");
        }

        if (empleadoService.Guardar(empleadoDTO)) {
            return ResponseEntity.ok("Empleado añadido correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El empleado no pudo ser añadido");
        }
    }
}
