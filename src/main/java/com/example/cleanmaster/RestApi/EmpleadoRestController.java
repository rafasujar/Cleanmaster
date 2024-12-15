package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.MailService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
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


            if (empleadoDTO2.getCorreo().isEmpty() && empleadoDTO2.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El empleadodto2.getCorreo o la contraseña no pueden estar vacios");
            }

            if (!empleadoDTO2.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El empleadodto2.getCorreo no cumple con los requisitos de formato");
            }
            empleadoDTO2.setPassword(utilsCleanMaster.decodeBase54(empleadoDTO2.getPassword()));
            if (!empleadoDTO2.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La contraseña no cumple con los requisitos de seguridad");
            }

           Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.logearEmpleado(empleadoDTO2.getCorreo(), empleadoDTO2.getPassword()));
            if (empleadoDTO.isPresent()) {
                empleadoDTO2 = empleadoDTO.get();

                return ResponseEntity.ok(utilsCleanMaster.generateToken(true, empleadoDTO2.getId(), empleadoDTO2.getCorreo(), empleadoDTO2.getNombre()+" "+empleadoDTO2.getApellidos()));


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


        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }

        //comprobar que el correo existe en la base de datos
        Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.existsByCorreo(correo));

        if (empleadoDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(false);
        }
        empleadoDTO2 = empleadoDTO.get();
        //generar una nueva contraseña
        for (int i = 0; i < 8; i++) {
            passwd.append((char) (random.nextInt(26) + 65));
        }
        passwd.append(random.nextInt(10));

        empleadoDTO2.setPassword(utilsCleanMaster.encodeBase64(passwd.toString()));
        empleadoService.Guardar(empleadoDTO2);
        mailService.resetPassword(correo, passwd.toString());
        return ResponseEntity.ok(true);

    }



    @PostMapping("areaempleados/home/{id}/savecambios")
    public ResponseEntity<?> saveCampos(@RequestHeader("Authorization") String token, @RequestBody EmpleadoDTO empleadoDTO, @PathVariable("id") int id) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El token no puede estar vacio");
        }
        if (empleadoDTO.getCorreo().isEmpty() && empleadoDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El empleadodto2.getCorreo o la contraseña no pueden estar vacios");
        }

        if (!empleadoDTO.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El empleadodto2.getCorreo no cumple con los requisitos de formato");
        }
        String decode = utilsCleanMaster.decodeBase54(empleadoDTO.getPassword());
        if (!decode.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La contraseña no cumple con los requisitos de seguridad");
        }

        Optional<EmpleadoDTO> empleadoDTO2 = Optional.ofNullable(empleadoService.findById(id));
        if (empleadoDTO2.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El empleado no fue encontrado");
        }
        empleadoDTO.setId(id);
        empleadoDTO.setIban(empleadoDTO2.get().getIban());
        empleadoDTO.setNumss(empleadoDTO2.get().getNumss());
        empleadoDTO.setIdEncargada(empleadoDTO2.get().getIdEncargada());
        empleadoService.Guardar(empleadoDTO);
        return ResponseEntity.ok(utilsCleanMaster.generateToken(true, empleadoDTO.getId(), empleadoDTO.getCorreo(), empleadoDTO.getNombre()+" "+empleadoDTO.getApellidos()));
    }

}
