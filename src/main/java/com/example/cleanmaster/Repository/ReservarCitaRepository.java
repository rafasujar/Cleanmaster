package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.ReservarCitaEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservarCitaRepository extends JpaRepository<ReservarCitaEntities, Integer> {
    @Query("SELECT r FROM ReservarCitaEntities r WHERE r.idCliente = :idCliente AND r.fecha BETWEEN :lunes AND :domingo and r.finalizadaReserva = false and r.idEmpleado is not null")
    List<ReservarCitaEntities> getReservarPorClienteEntreLunesYDomingo(Integer idCliente, LocalDate lunes, LocalDate domingo);

    @Query("SELECT r FROM ReservarCitaEntities r WHERE r.idEmpleado = :idEmpleado AND r.fecha BETWEEN :lunes AND :domingo and r.finalizadaReserva = false and r.idEmpleado is not null")
    List<ReservarCitaEntities> getReservarPorEmpleadoEntreLunesYDomingo(Integer idEmpleado, LocalDate lunes, LocalDate domingo);

    Optional<ReservarCitaEntities> findByIdAndAndIdEmpleado(Integer idReserva, Integer idEmpleado);

    List<ReservarCitaEntities> findAllByIdClienteAndFinalizadaReserva(int idCliente, boolean b);

    Collection<ReservarCitaEntities> findAllByIdEmpleadoAndFinalizadaReserva(int i, boolean b);


    @Query("SELECT r FROM ReservarCitaEntities r " +
            "WHERE r.idEmpleado = :idEmpleado " +
            "AND FUNCTION('MONTH', r.fecha) = :dayOfMonth " )
    Collection<ReservarCitaEntities> findAllByIdEmpleadoAndDayOfMonth(@Param("idEmpleado") Integer idEmpleado,
                                                                      @Param("dayOfMonth") int dayOfMonth);

    @Query("SELECT r FROM ReservarCitaEntities r " +
            "WHERE r.idCliente = :idCliente " +
            "AND FUNCTION('MONTH', r.fecha) = :dayOfMonth " )
    Collection<ReservarCitaEntities> findAllByIdClienteAndDayOfMonth(@Param("idCliente") Integer idCliente,
                                                                     @Param("dayOfMonth") int dayOfMonth);
}
