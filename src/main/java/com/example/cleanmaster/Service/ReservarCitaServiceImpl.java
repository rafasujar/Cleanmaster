package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.ReservarCitaRepository;
import com.example.cleanmaster.models.dto.ReservarCitaDTO;
import com.example.cleanmaster.models.entities.ReservarCitaEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservarCitaServiceImpl implements ReservarCitaService {
    @Autowired
    private ReservarCitaRepository reservarCitaRepository;

    @Override
    public List<ReservarCitaDTO> reservasPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo) {

        List<ReservarCitaDTO> reservarCitaDTOList = new ArrayList<>();
        for (ReservarCitaEntities e : reservarCitaRepository.getReservarPorClienteEntreLunesYDomingo(idCliente, lunes, domingo)) {
            reservarCitaDTOList.add(ReservarCitaDTO.ConvertToDTO(e));
        }

        return Optional.of(reservarCitaDTOList).orElse(null);
    }

    @Override
    public List<ReservarCitaDTO> reservasPorEmpleadosEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo) {
        List<ReservarCitaDTO> reservarCitaDTOList = new ArrayList<>();
        for (ReservarCitaEntities e : reservarCitaRepository.getReservarPorEmpleadoEntreLunesYDomingo(idEmpleado, lunes, domingo)) {
            reservarCitaDTOList.add(ReservarCitaDTO.ConvertToDTO(e));
        }

        return Optional.of(reservarCitaDTOList).orElse(null);
    }

    @Override
    public boolean finalizarReserva(Integer idReserva, Integer idEmpleado) {
        Optional<ReservarCitaEntities> reserva = reservarCitaRepository.findByIdAndAndIdEmpleado(idReserva, idEmpleado);
        if (reserva.isPresent()) {
            ReservarCitaEntities r = reserva.get();
            r.setFinalizadaReserva(true);
            reservarCitaRepository.save(r);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void save(ReservarCitaDTO reservarCitaDTO) {
        reservarCitaRepository.save(ReservarCitaDTO.ConvertToEntities(reservarCitaDTO));
    }

    @Override
    public List<ReservarCitaDTO> historialCliente(int idCliente) {
        return reservarCitaRepository.findAllByIdClienteAndFinalizadaReserva(idCliente, true).stream().map(ReservarCitaDTO::ConvertToDTO).toList();
    }

    @Override
    public List<ReservarCitaDTO> obtenerReservasParaHorarios() {
        return reservarCitaRepository.findAllByIdEmpleadoAndFinalizadaReserva(null, false).stream().map(ReservarCitaDTO::ConvertToDTO).toList();
    }

    @Override
    public List<ReservarCitaDTO> obtenerReservasParaMensajePorIdEmpleadoSegunMes(Integer idEmpleado) {
        Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.of(reservarCitaRepository.findAllByIdEmpleadoAndDayOfMonth(idEmpleado, LocalDate.now().getMonthValue()).stream().map(ReservarCitaDTO::ConvertToDTO).toList());
        return reservarCitaDTOList.orElse(null);
    }

    @Override
    public List<ReservarCitaDTO> obtenerReservasParaMensajePorIdClienteSegunMes(Integer idCliente) {
        Optional<List<ReservarCitaDTO>> reservarCitaDTOList = Optional.of(reservarCitaRepository.findAllByIdClienteAndDayOfMonth(idCliente, LocalDate.now().getMonthValue()).stream().map(ReservarCitaDTO::ConvertToDTO).toList());

        return reservarCitaDTOList.orElse(null);
    }

    @Override
    public ReservarCitaDTO findById(Integer id) {
        return reservarCitaRepository.findById(id).map(ReservarCitaDTO::ConvertToDTO).orElse(null);
    }


}
