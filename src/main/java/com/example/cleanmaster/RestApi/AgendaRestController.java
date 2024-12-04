package com.example.cleanmaster.RestApi;

import com.example.cleanmaster.Service.*;
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

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/api/agenda/veragenda/semana")
    public ResponseEntity<?> verAgenda(@RequestBody String token) throws JsonProcessingException {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        System.out.println(token);

        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, ArrayNode> reservas = Map.of(
                1, mapper.createArrayNode(),
                2, mapper.createArrayNode(),
                3, mapper.createArrayNode(),
                4, mapper.createArrayNode(),
                5, mapper.createArrayNode(),
                6, mapper.createArrayNode(),
                7, mapper.createArrayNode()
        );
        Optional<List<ReservarCitaDTO>> reservarCitaDTOList;
        String[] dias = {null,"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        //obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        //obtener el lunes de la semana actual
        LocalDate monday = fechaActual.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        //obtener el domingo de la semana actual
        LocalDate sunday = fechaActual.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(token));
        if (jsonNodeRoot.get("empleado").asBoolean()) {
            reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorEmpleadosEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), monday, sunday));
        }else{
            reservarCitaDTOList = Optional.ofNullable(reservarCitaService.reservasPorClienteEntreLunesYDomingo(jsonNodeRoot.get("id").asInt(), monday, sunday));
        }

        if (reservarCitaDTOList.isPresent()) {
                for (ReservarCitaDTO reservarCitaDTO : reservarCitaDTOList.get()) {
                    ObjectNode mapjson = mapper.createObjectNode();
                    mapjson.put("id", reservarCitaDTO.getId());
                    String d = dias[reservarCitaDTO.getFecha().getDayOfWeek().getValue()];
                    mapjson.put("fecha", d+", " + reservarCitaDTO.getFecha().getDayOfMonth() + " de " + reservarCitaDTO.getFecha().getMonth() + " de " + reservarCitaDTO.getFecha().getYear());
                    mapjson.put("tipo", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());

                    mapjson.put("empleado",jsonNodeRoot.get("empleado").asBoolean() );

                    if (jsonNodeRoot.get("empleado").asBoolean()) {

                        mapjson.put("direccion", direccionesService.getDireccionById(reservarCitaDTO.getIdDireccion()).getDireccion());
                        mapjson.put("cliente", clienteService.findById(reservarCitaDTO.getIdCliente()).getNombre());
                        mapjson.put("movil", clienteService.findById(reservarCitaDTO.getIdCliente()).getMovil());

                    }else{

                        mapjson.put("asistente", empleadoService.findById(reservarCitaDTO.getIdEmpleado()).getNombre());

                    }
                    reservas.get(reservarCitaDTO.getFecha().getDayOfWeek().getValue()).add(mapjson);
                }
            }else{
                return ResponseEntity.status(404).body("No hay reservas para esta semana");
            }

        return ResponseEntity.ok(reservas);
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
