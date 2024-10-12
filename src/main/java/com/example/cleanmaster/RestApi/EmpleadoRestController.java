package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.MailService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private MailService mailService;

    @PostMapping("/AreaEmpleado/api/login")
    public ResponseEntity<?> loginEmpleado(@RequestBody EmpleadoDTO empleadoDTO2) {

        String respuesta;

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


           Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.logearEmpleado(empleadoDTO2.getCorreo(), empleadoDTO2.getPassword()));
            if (empleadoDTO.isPresent()) {
                empleadoDTO2 = empleadoDTO.get();

                respuesta = "{" +
                        "\"empleado\": " + true + "," +
                        "\"correo\": \"" + empleadoDTO2.getCorreo() + "\"," +
                        "\"id\": " + empleadoDTO2.getId() + "," +
                        "\"nombre\": \"" + empleadoDTO2.getNombre() + "\"," +
                        "\"apellido\": \"" + empleadoDTO2.getApellidos() + "\"" +
                        "}";
                System.out.println(respuesta);
                return ResponseEntity.ok(respuesta);



            } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado no fue encontrado");
        }
    }

    @PostMapping("/AreaEmpleado/api/resetpasswd")
    public ResponseEntity<?> resepassword(@RequestBody String correo){
        StringBuilder passwd = new StringBuilder();
        Random random = new Random();
        EmpleadoDTO empleadoDTO2 ;
        //comprobar que el correo tiene el formato correcto

        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El correo no cumple con los requisitos de formato");
        }

        //comprobar que el correo existe en la base de datos
        Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.existsByCorreo(correo));

        if (empleadoDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El correo no existe en la base de datos");
        }
        empleadoDTO2 = empleadoDTO.get();

        //generar una nueva contraseña
        for (int i = 0; i < 8; i++) {
            passwd.append((char) (random.nextInt(26) + 65));
        }
        passwd.append(random.nextInt(10));

        empleadoDTO2.setPassword(passwd.toString());
        empleadoService.Guardar(empleadoDTO2);
        mailService.resetPassword(correo, passwd.toString());



        return ResponseEntity.ok("Correo enviado");

    }
/*
   @DeleteMapping("/AreaEmpleado/api/eliminarEmpleado")
    public ResponseEntity<?> logoutEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        return ResponseEntity.ok(empleadoService.eliminarEmpleado(empleadoDTO.getId()));
    }
    @PutMapping("/AreaEmpleado/api/actualizarEmpleado")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        empleadoService.update(empleadoDTO, empleadoDTO.getId());
        return ResponseEntity.ok("Empleado actualizado");

    }*/
}
