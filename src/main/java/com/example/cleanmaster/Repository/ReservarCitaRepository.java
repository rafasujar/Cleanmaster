package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.ReservarCitaEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservarCitaRepository extends JpaRepository<ReservarCitaEntities, Integer> {
    @Query("SELECT r FROM ReservarCitaEntities r WHERE r.idCliente = :idCliente AND r.fecha BETWEEN :lunes AND :domingo")
    List<ReservarCitaEntities> getReservarPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo);

    @Query("SELECT r FROM ReservarCitaEntities r WHERE r.idEmpleado = :idEmpleado AND r.fecha BETWEEN :lunes AND :domingo")
    List<ReservarCitaEntities> getReservarPorEmpleadoEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo);

    Optional<ReservarCitaEntities> findByIdAndAndIdEmpleado(Integer idReserva, Integer idEmpleado);
}
