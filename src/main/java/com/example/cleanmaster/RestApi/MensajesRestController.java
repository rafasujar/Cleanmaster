package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.MensajesService;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensajesRestController {
    @Autowired
    private MensajesService mensajesService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/api/mensajes/obtenerMensaje/inicio")
    public ResponseEntity<?> obtenerMensajesInicio(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("Error al obtener mensajes");
    }

}
