package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ClienteService;
import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.MensajesService;
import com.example.cleanmaster.models.dto.MensajeDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MensajesRestController {
    private static final Logger log = LogManager.getLogger(MensajesRestController.class);
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
            List<MensajeDTO> mensajes;
            if (node.get("empleado").asBoolean()) {
                mensajes = mensajesService.findAllByIdEmpleado(node.get("id").asInt());
            } else {
                mensajes = mensajesService.findAllByIdCliente(node.get("id").asInt());
            }
            if (mensajes.isEmpty()) {
                return ResponseEntity.status(404).body("No hay mensajes");
            }

           return ResponseEntity.ok(mensajes);
        } catch (Exception e) {
            log.error("e: ", e);
        }
        return ResponseEntity.status(500).body("Error al obtener mensajes");
    }

    @PostMapping("/api/mensajes/crearMensaje")
    public ResponseEntity<?> crearMensaje(@RequestHeader("Authorization") String token,@RequestBody MensajeDTO me) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (node.get("empleado").asBoolean()) {
                me.setIdEmpleado(node.get("id").asInt());
                me.setIdCliente(me.getId());
                me.setId(null);
                me.setReceptor(clienteService.findById(me.getIdCliente()).getNombre());
            } else {
                me.setIdCliente(node.get("id").asInt());
                me.setIdEmpleado(me.getId());
                me.setId(null);
                me.setReceptor(empleadoService.findById(me.getIdEmpleado()).getNombre());
            }
            me.setEmisor(node.get("nombre").asText());
            mensajesService.save(me);
            return ResponseEntity.ok("Mensaje creado");
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.status(500).body("Error al crear mensaje");
        }
    }

}
