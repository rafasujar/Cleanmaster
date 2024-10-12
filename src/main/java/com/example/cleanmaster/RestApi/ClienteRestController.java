package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.models.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/areaclientes/api/login")
    public ResponseEntity<?> login(@RequestBody ClienteDTO clienteDTO) {
        String respuesta;
        if (clienteDTO.getCorreo().isEmpty() && clienteDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("El correo o la contraseña no pueden estar vacios");
        }
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
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El cliente no fue encontrado");

        }
    }


}
