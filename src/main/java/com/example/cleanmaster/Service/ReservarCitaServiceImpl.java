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
public class ReservarCitaServiceImpl implements ReservarCitaService{
    @Autowired
    private ReservarCitaRepository reservarCitaRepository;

    @Override
    public List<ReservarCitaDTO> reservasPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo) {

        List<ReservarCitaDTO> reservarCitaDTOList = new ArrayList<>();
               for (ReservarCitaEntities e : reservarCitaRepository.getReservarPorClienteEntreLunesYDomingo(idCliente, lunes, domingo)){
                     reservarCitaDTOList.add(ReservarCitaDTO.ConvertToDTO(e));
               }

        return Optional.of(reservarCitaDTOList).orElse(null);
    }

    @Override
    public List<ReservarCitaDTO> reservasPorEmpleadosEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo) {
        List<ReservarCitaDTO> reservarCitaDTOList = new ArrayList<>();
        for (ReservarCitaEntities e : reservarCitaRepository.getReservarPorEmpleadoEntreLunesYDomingo(idEmpleado, lunes, domingo)){
            reservarCitaDTOList.add(ReservarCitaDTO.ConvertToDTO(e));
        }

        return Optional.of(reservarCitaDTOList).orElse(null);
    }
}
