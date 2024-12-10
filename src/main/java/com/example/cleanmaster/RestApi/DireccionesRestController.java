package com.example.cleanmaster.RestApi;


import com.example.cleanmaster.Service.DireccionesService;
import com.example.cleanmaster.models.dto.DireccionesDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DireccionesRestController {
    @Autowired
    private DireccionesService direccionesService;

    @PostMapping("/api/direciones/obtenerDirecciones")
    public ResponseEntity<?> obtenerDirecciones(@RequestBody String token) {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String tokenDecoded = utilsCleanMaster.decoderUser(token);
            JsonNode jsonNodeRoot = mapper.readTree(tokenDecoded);
            if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para obtener direcciones");
            }else{
                return ResponseEntity.ok(direccionesService.findAllByIdCliente(jsonNodeRoot.get("id").asInt()));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al decodificar el token");
        }
    }

    @PostMapping("/api/direciones/guardarNuevaDireccion")
    public ResponseEntity<?> guardarNuevaDireccion(@RequestHeader("Authorization") String token, @RequestBody DireccionesDTO direccionesDTO){
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        String decodedToken = utilsCleanMaster.decoderUser(token);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(decodedToken);
            if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para guardar direcciones");
            }else{
                direccionesDTO.setIdCliente(jsonNodeRoot.get("id").asInt());
                direccionesService.save(direccionesDTO);
                return ResponseEntity.ok("Direccion guardada correctamente");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al decodificar el token");
        }
    }

    @PostMapping("/api/direciones/eleminarDireccion")
    public ResponseEntity<?> eleminarDireccion(@RequestHeader("Authorization") String token, @RequestBody DireccionesDTO direccionesDTO){
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        String decodedToken = utilsCleanMaster.decoderUser(token);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(decodedToken);
            if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para guardar direcciones");
            }else{
                direccionesDTO.setIdCliente(jsonNodeRoot.get("id").asInt());
                direccionesService.delete(direccionesDTO);
                return ResponseEntity.ok("Direccion eliminada correctamente");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al decodificar el token");
        }

    }
}