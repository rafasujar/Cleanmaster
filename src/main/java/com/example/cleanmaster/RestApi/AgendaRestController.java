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
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private MailService mailSender;

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
                        String nombreEmpleado = empleadoService.findById(reservarCitaDTO.getIdEmpleado()).getNombre();
                        mapjson.put("asistente", nombreEmpleado);

                    }
                    reservas.get(reservarCitaDTO.getFecha().getDayOfWeek().getValue()).add(mapjson);
                }
            return ResponseEntity.ok(reservas);
            }else{
                return ResponseEntity.status(404).body("No hay reservas para esta semana");
            }


    }

    @PostMapping("/api/agenda/reservarfinalizada")
    public ResponseEntity<?> reservarFinalizada(@RequestBody String token) throws JsonProcessingException {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(token);
        if(responseJson.get("token").asText().isEmpty()){
            return ResponseEntity.badRequest().body("empty token ");
        }
        JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(responseJson.get("token").asText()));
        if (jsonNodeRoot.get("empleado").asBoolean()) {
            if (reservarCitaService.finalizarReserva(responseJson.get("id").asInt(), jsonNodeRoot.get("id").asInt())) {
                return ResponseEntity.ok("Reserva finalizada");
            }else{
                    return ResponseEntity.badRequest().body("No tienes permisos para finalizar reservas");
                }
        }else{
            return ResponseEntity.badRequest().body("No tienes permisos para finalizar reservas");
        }

    }


    @GetMapping("/api/obtenerTiposServicios")
    public ResponseEntity<?> obtenerTiposServicios() {
        return ResponseEntity.ok(tiposServiciosService.getAllTipos());
    }



    @PostMapping("/api/agenda/reservarcita")
    public ResponseEntity<?> reservarCita(@RequestHeader("Authorization") String token, @RequestBody ReservarCitaDTO reservarCitaDTO) {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String tokenDecoded = utilsCleanMaster.decoderUser(token);
            JsonNode jsonNodeRoot = mapper.readTree(tokenDecoded);

            if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para reservar citas");
            }else{
                reservarCitaDTO.setIdCliente(jsonNodeRoot.get("id").asInt());
                reservarCitaDTO.setFinalizadaReserva(false);
                reservarCitaService.save(reservarCitaDTO);
                return ResponseEntity.ok("Reserva realizada");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/historial/cliente")
    public ResponseEntity<?> historialCliente(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String[] dias = {null,"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
            String tokenDecoded = utilsCleanMaster.decoderUser(token);
            ArrayNode arrayNode = mapper.createArrayNode();
            JsonNode jsonNodeRoot = mapper.readTree(tokenDecoded);
            if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para ver historial de citas");
            }else{
                Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.ofNullable(reservarCitaService.historialCliente(jsonNodeRoot.get("id").asInt()));
                if (reservarCitaDTOList.isPresent()) {
                    for (ReservarCitaDTO reservarCitaDTO : reservarCitaDTOList.get()) {
                        ObjectNode mapjson = mapper.createObjectNode();
                        mapjson.put("id", reservarCitaDTO.getFecha().toString());
                        String d = dias[reservarCitaDTO.getFecha().getDayOfWeek().getValue()];
                        mapjson.put("fecha", d + ", " + reservarCitaDTO.getFecha().getDayOfMonth() + " de " + reservarCitaDTO.getFecha().getMonth() + " de " + reservarCitaDTO.getFecha().getYear());
                        mapjson.put("tipo", tiposServiciosService.getTipoById(reservarCitaDTO.getIdTipoServicio()).getNombre());
                        mapjson.put("asistente", empleadoService.findById(reservarCitaDTO.getIdEmpleado()).getNombre());
                        mapjson.put("idreserva", reservarCitaDTO.getId());
                        arrayNode.add(mapjson);
                    }
                    return ResponseEntity.ok(arrayNode);
                }else{
                    return ResponseEntity.status(404).body("No hay reservas para esta semana");
                }

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/historial/cliente/factura")
    public ResponseEntity<?> facturacliente(@RequestHeader("authorization") String token, @RequestBody String reserva) {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        try {
         ObjectMapper mapper = new ObjectMapper();
         JsonNode jsonNodeRoot = mapper.readTree(utilsCleanMaster.decoderUser(token));
         if (jsonNodeRoot.get("empleado").asBoolean()) {
                return ResponseEntity.badRequest().body("No tienes permisos para ver historial de citas");
         }
         JsonNode jsonNodeReserva = mapper.readTree(reserva);
         StringBuilder cuerpoMensaje = new StringBuilder();
            System.out.println(reserva);
            cuerpoMensaje.append("Factura de la reserva: ").append(jsonNodeReserva.get("idreserva").asText()).append("\n");
            cuerpoMensaje.append("Fecha: ").append(jsonNodeReserva.get("fecha").asText()).append("\n");
            cuerpoMensaje.append("Tipo de servicio: ").append(jsonNodeReserva.get("tipo").asText()).append("\n");
            cuerpoMensaje.append("Asistente: ").append(jsonNodeReserva.get("asistente").asText()).append("\n");
            cuerpoMensaje.append("Gracias por preferirnos");
            //mailSender.sendFacture(jsonNodeRoot.get("correo").asText(), cuerpoMensaje.toString());
            mailSender.sendFacture("rafasujar@yopmail.com", cuerpoMensaje.toString());

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(reserva);
        return ResponseEntity.ok("Factura enviada");
    }

    @PostMapping("/api/obtenerReservas")
    public ResponseEntity<?> obtenerReservas(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("empty token ");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            String tokenDecoded = utilsCleanMaster.decoderUser(token);
            JsonNode jsonNodeRoot = mapper.readTree(tokenDecoded);
            if (jsonNodeRoot.get("empleado").asBoolean()) {
               List<ReservarCitaDTO> reservarCitaDTOList = reservarCitaService.obtenerReservasParaHorarios();
                if (reservarCitaDTOList.isEmpty()) {
                    return ResponseEntity.status(404).body("No hay reservas para esta semana");
                }
                return ResponseEntity.ok(reservarCitaDTOList);
            }
            return ResponseEntity.badRequest().body("No tienes permisos para ver reservas");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
