package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.ReservarCitaService;
import com.example.cleanmaster.Service.TiposServiciosService;
import com.example.cleanmaster.models.dto.ReservarCitaDTO;
import com.example.cleanmaster.utils.utilsCleanMaster;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    @Autowired
    private TiposServiciosService tiposServiciosService;

    @Autowired
    private

    @PostMapping("/api/agenda/veragenda/semana")
    public ResponseEntity<?> verAgenda(@RequestBody String token) throws JsonProcessingException {

        if (token.isEmpty()){
            return ResponseEntity.badRequest().body("empty token ");
        }
        String respuesta;
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode lunes = mapper.createArrayNode();
        ArrayNode martes = mapper.createArrayNode();
        ArrayNode miercoles = mapper.createArrayNode();
        ArrayNode jueves = mapper.createArrayNode();
        ArrayNode viernes = mapper.createArrayNode();
        ArrayNode sabado = mapper.createArrayNode();
        ArrayNode domingo = mapper.createArrayNode();
        //obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        //obtener el lunes de la semana actual
        LocalDate monday  = fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        //obtener el domingo de la semana actual
        LocalDate sunday = fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(token));

        if (jsonNodeRoot.get("empleado").asBoolean()){

             Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorEmpleadosEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), monday, sunday));
            if (reservarCitaDTOList.isPresent()) {
                for (ReservarCitaDTO reservarCitaDTO : reservarCitaDTOList.get()) {
                    ObjectNode mapjson = mapper.createObjectNode();
                    switch (reservarCitaDTO.getFecha().getDayOfWeek()){
                        case MONDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Lunes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion",  );
                            lunes.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case TUESDAY:
                            martes.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case WEDNESDAY:
                            miercoles.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case THURSDAY:
                            jueves.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case FRIDAY:
                            viernes.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case SATURDAY:
                            sabado.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                        case SUNDAY:
                            domingo.add(mapper.valueToTree(reservarCitaDTO));
                            break;
                    }
                }
            respuesta = " as";

                return ResponseEntity.ok(respuesta);
            }else {

                return ResponseEntity.status(404).body("No hay reservas para este empleado");

            }

        }else{
            Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorClienteEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), monday, sunday));
            if (reservarCitaDTOList.isPresent()) {

                return ResponseEntity.ok(reservarCitaDTOList.get());



            }else {
                return ResponseEntity.status(404).body("No hay reservas para este empleado");
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
