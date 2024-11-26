package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.Service.MailService;
import com.example.cleanmaster.models.dto.ClienteDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;

@RestController
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MailService mailService;

    @PostMapping("/areaclientes/api/login")
    public ResponseEntity<?> login(@RequestBody ClienteDTO clienteDTO) {
        String respuesta;
        if (clienteDTO.getCorreo().isEmpty() && clienteDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("El correo o la contraseña no pueden estar vacios");
        }
        clienteDTO.setPassword(utilsCleanMaster.decoderUser(clienteDTO.getPassword()));
        if (!clienteDTO.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cliente no cumple con los requisitos de formato");
        }

        if (!clienteDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La contraseña no cumple con los requisitos de seguridad");
        }

        Optional<ClienteDTO> clienteDTO1 = Optional.ofNullable(clienteService.logearCliente(clienteDTO));

        if (clienteDTO1.isPresent()) {
            clienteDTO = clienteDTO1.get();
            respuesta = "{" +
                    "\"empleado\": " + false + "," +
                    "\"correo\": \"" + clienteDTO.getCorreo() + "\"," +
                    "\"id\": " + clienteDTO.getId() + "," +
                    "\"nombre\": \"" + clienteDTO.getNombre() + "\"" +
                    "}";

            System.out.println(respuesta);
            return ResponseEntity.ok(utilsCleanMaster.encodeUser(respuesta));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El cliente no fue encontrado");

        }
    }

    @PostMapping("/AreaCliente/api/resetpasswd")
    public ResponseEntity<?> resepassword(@RequestBody String correo){
        StringBuilder passwd = new StringBuilder();
        Random random = new Random();
        ClienteDTO clienteDTO;
        if (correo.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }


        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
        Optional<ClienteDTO> clienteDTO1 = Optional.ofNullable(clienteService.existeCliente(correo));
        if (clienteDTO1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(false);
        }
        clienteDTO = clienteDTO1.get();
        for (int i = 0; i < 8; i++) {
            passwd.append(random.nextInt(10));
        }
            clienteDTO.setPassword(passwd.toString());
            clienteService.registrarCliente(clienteDTO);
            mailService.resetPassword(correo, passwd.toString());
            return ResponseEntity.ok(true);

    }


    @PostMapping("/areaclientes/api/registro")
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteDTO clienteDTO){
        String respuesta;
        if (clienteDTO.getCorreo().isEmpty() && clienteDTO.getPassword().isEmpty() && clienteDTO.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("los campos estan vacios");
        }
        if (!clienteDTO.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cliente no cumple con los requisitos de formato");
        }
        if (!utilsCleanMaster.decoderUser(clienteDTO.getPassword()).matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La contraseña no cumple con los requisitos de seguridad");
        }
        if (!clienteDTO.getMovil().matches("^[67]\\d{8}$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El movil no cumple con los requisitos de formato");
        }

        if (clienteService.existeCliente(clienteDTO.getCorreo()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El cliente ya existe");
        }
        respuesta = "{" +
                "\"empleado\": " + false + "," +
                "\"correo\": \"" + clienteDTO.getCorreo() + "\"," +
                "\"id\": " + clienteDTO.getId() + "," +
                "\"nombre\": \"" + clienteDTO.getNombre() + "\"" +
                "}";
        clienteService.registrarCliente(clienteDTO);
        return ResponseEntity.ok(utilsCleanMaster.encodeUser(respuesta));
    }



}
