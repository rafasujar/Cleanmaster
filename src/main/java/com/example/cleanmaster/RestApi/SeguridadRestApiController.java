package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeguridadRestApiController {

    @PostMapping("/api/seguridad/obteneerhome")
    public ResponseEntity<?> obtenerHome(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (node.get("empleado").asBoolean()) {
                return ResponseEntity.ok("/areaempleados/home/"+node.get("id").asInt());
            } else {
                return ResponseEntity.ok("/areaclientes/home/"+node.get("id").asInt());
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener mensajes");
        }
    }



}
