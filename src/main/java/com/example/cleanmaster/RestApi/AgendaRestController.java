package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.DireccionesService;
import com.example.cleanmaster.Service.ReservarCitaService;
import com.example.cleanmaster.Service.TiposServiciosService;
import com.example.cleanmaster.models.dto.ReservarCitaDTO;
import com.example.cleanmaster.models.dto.TiposServiciosDTO;
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
import java.util.Map;
import java.util.Optional;

@RestController
public class AgendaRestController {
    @Autowired
    private  ReservarCitaService reservarCitaService;
    @Autowired
    private TiposServiciosService tiposServiciosService;

    @Autowired
    private DireccionesService direccionesService;

    @PostMapping("/api/agenda/veragenda/semana")
    public ResponseEntity<?> verAgenda(@RequestBody String token) throws JsonProcessingException {
        if (token.isEmpty()){
            return ResponseEntity.badRequest().body("empty token ");
        }
        System.out.println(token);

        ObjectMapper mapper = new ObjectMapper();
        Map<DayOfWeek, ArrayNode> reservas = Map.of(
                DayOfWeek.MONDAY, mapper.createArrayNode(),
                DayOfWeek.TUESDAY, mapper.createArrayNode(),
                DayOfWeek.WEDNESDAY, mapper.createArrayNode(),
                DayOfWeek.THURSDAY, mapper.createArrayNode(),
                DayOfWeek.FRIDAY, mapper.createArrayNode(),
                DayOfWeek.SATURDAY, mapper.createArrayNode(),
                DayOfWeek.SUNDAY, mapper.createArrayNode()
        );

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
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.MONDAY).add(mapjson);
                            break;
                        case TUESDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Martes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.TUESDAY).add(mapjson);
                            break;
                        case WEDNESDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Miercoles, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion() );
                            reservas.get(DayOfWeek.WEDNESDAY).add(mapjson);
                            break;
                        case THURSDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Jueves, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.THURSDAY).add(mapjson);
                            break;
                        case FRIDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Viernes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.FRIDAY).add(mapjson);
                            break;
                        case SATURDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Sabado, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.SATURDAY).add(mapjson);
                            break;
                        case SUNDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Domingo, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                            reservas.get(DayOfWeek.SUNDAY).add(mapjson);
                            break;
                    }
                }

                return ResponseEntity.ok(reservas);
            }else {
                return ResponseEntity.status(404).body("No hay reservas para este empleado");
            }

        }else{
            Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorClienteEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), monday, sunday));
            if (reservarCitaDTOList.isPresent()) {
                for (ReservarCitaDTO reservarCitaDTO : reservarCitaDTOList.get()) {
                    ObjectNode mapjson = mapper.createObjectNode();
                    switch (reservarCitaDTO.getFecha().getDayOfWeek()){
                        case MONDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Lunes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            TiposServiciosDTO tiposServiciosDTO =  tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio());
                            mapjson.put("tipo de servicio",tiposServiciosDTO.getNombre());
                            break;
                        case TUESDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Martes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.TUESDAY).add(mapjson);
                            break;
                        case WEDNESDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Miercoles, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.WEDNESDAY).add(mapjson);
                            break;
                        case THURSDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Jueves, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.THURSDAY).add(mapjson);
                            break;
                        case FRIDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Viernes, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.FRIDAY).add(mapjson);
                            break;
                        case SATURDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Sabado, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.SATURDAY).add(mapjson);
                            break;
                        case SUNDAY:
                            mapjson.put("id", reservarCitaDTO.getId());
                            mapjson.put("fecha", "Domingo, "+reservarCitaDTO.getFecha().getDayOfMonth()+" de "+reservarCitaDTO.getFecha().getMonth()+" de "+reservarCitaDTO.getFecha().getYear());
                            mapjson.put("tipo de servicio", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                            reservas.get(DayOfWeek.SUNDAY).add(mapjson);
                            break;
                    }
                }
                return ResponseEntity.status(200).body(reservas);
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
