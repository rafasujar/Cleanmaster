package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ReservarCitaService;
import com.example.cleanmaster.models.dto.ReservarCitaDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@RestController
public class AgendaRestController {
    @Autowired
    private  ReservarCitaService reservarCitaService;

    @PostMapping("/api/agenda/veragenda/semana")
    public ResponseEntity<?> verAgenda(@RequestBody String token) throws JsonProcessingException {

        if (token.isEmpty()){
            return ResponseEntity.badRequest().body("empty token ");
        }
        LocalDate fechaActual = LocalDate.now();
        LocalDate lunes = fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate domingo = fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(token));
        if (jsonNodeRoot.get("empleado").asBoolean()){
            Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorEmpleadosEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), lunes, domingo));
            if (reservarCitaDTOList.isPresent()) {
                return ResponseEntity.ok(reservarCitaDTOList.get());
            }else {
                return ResponseEntity.badRequest().body("No hay reservas para este empleado");
            }
        }else{
            Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorClienteEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), lunes, domingo));
            if (reservarCitaDTOList.isPresent()) {
                return ResponseEntity.ok(reservarCitaDTOList.get());
            }else {
                return ResponseEntity.badRequest().body("No hay reservas para este empleado");
            }
        }
    }
/*
    @PostMapping("/api/agenda/reservarcita")
    public ResponseEntity<?> reservarCita(@RequestBody String token) throws JsonProcessingException {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(token));
        if (jsonNodeRoot.get("empleado").asBoolean()) {
            return ResponseEntity.badRequest().body("Solo los clientes pueden reservar citas");
        } else {

        }

*/
}
