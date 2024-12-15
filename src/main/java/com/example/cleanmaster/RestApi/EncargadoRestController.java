package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.EmpleadoService;
import com.example.cleanmaster.Service.EncargadoService;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EncargadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EncargadoService encargadoService;


    @PostMapping("/api/altaempleado")
    public ResponseEntity<?> altaEmpleado(@RequestHeader("Authorization") String token, @RequestBody EmpleadoDTO empleadoDTO) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (!encargadoService.esEncargado(node.get("id").asInt())) {
                return ResponseEntity.status(401).body("No autorizado");
            }
            empleadoDTO.setIdEncargada(node.get("id").asInt());
            empleadoService.Guardar(empleadoDTO);

            return ResponseEntity.ok(empleadoService.existsByCorreo(empleadoDTO.getCorreo()).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("Error al dar de alta empleado");
    }

    @PostMapping("/api/altaempleado/servicios/{id}")
    public ResponseEntity<?> altaEmpleadoServicio(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody Integer[] idTiposservicios) throws Exception {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (!encargadoService.esEncargado(node.get("id").asInt())) {
                return ResponseEntity.status(401).body("No autorizado");
            }
            if (idTiposservicios.length == 0) {
                return ResponseEntity.status(400).body("No se han seleccionado servicios");
            }
            EmpleadoDTO empleadoDTO = empleadoService.findById(id);
            if (empleadoDTO == null) {
                return ResponseEntity.status(404).body("Empleado no encontrado");
            }
            for (Integer t : idTiposservicios) {
                empleadoService.asignarServicio(empleadoDTO.getId(), t);
            }
            return ResponseEntity.ok("Servicios asignados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al dar de alta empleado en servicios");
        }
    }

    @PostMapping("/api/bajaempleado")
    public ResponseEntity<?> bajaEmpleado(@RequestHeader("Authorization") String token, @RequestBody String correo) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (!encargadoService.esEncargado(node.get("id").asInt())) {
                return ResponseEntity.status(401).body("No autorizado");
            }
            Optional<EmpleadoDTO> empleadoDTO = Optional.ofNullable(empleadoService.existsByCorreo(correo));
            if (empleadoDTO.isPresent()) {
                empleadoService.eliminarEmpleado(empleadoDTO.get().getId());
                return ResponseEntity.ok("Empleado dado de baja");
            } else {
                return ResponseEntity.status(404).body("Empleado no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al dar de baja empleado");
        }
    }

    @PostMapping("/api/cargarempleados")
    public ResponseEntity<?> cargarEmpleados(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("No autorizado");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node  = mapper.readTree(utilsCleanMaster.decoderUser(token));
            if (!encargadoService.esEncargado(node.get("id").asInt())) {
                return ResponseEntity.status(401).body("No autorizado");
            }
            return ResponseEntity.ok(empleadoService.findAllByIdEncargado(node.get("id").asInt()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cargar empleados");
        }
    }

}
