package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ReservarCitaDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservarCitaService {
    List<ReservarCitaDTO> reservasPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo);
   List<ReservarCitaDTO> reservasPorEmpleadosEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo);
}
