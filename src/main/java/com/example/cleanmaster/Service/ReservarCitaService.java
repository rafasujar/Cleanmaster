package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ReservarCitaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservarCitaService {
    List<ReservarCitaDTO> reservasPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo);
   List<ReservarCitaDTO> reservasPorEmpleadosEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo);
   boolean finalizarReserva(Integer idReserva,Integer idEmpleado);
    void save(ReservarCitaDTO reservarCitaDTO);
    List<ReservarCitaDTO>historialCliente(int idCliente);

    List<ReservarCitaDTO> obtenerReservasParaHorarios();

    List<ReservarCitaDTO> obtenerReservasParaMensajePorIdEmpleadoSegunMes( Integer idEmpleado);

    List<ReservarCitaDTO> obtenerReservasParaMensajePorIdClienteSegunMes( Integer idCliente);

    ReservarCitaDTO findById(Integer id);
}
