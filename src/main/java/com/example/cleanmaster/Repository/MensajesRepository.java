package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.MensajeEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensajesRepository extends JpaRepository<MensajeEntities, Integer> {
    Optional<List<MensajeEntities>> findAllByIdEmpleado(Integer idEmpleado);

    Optional<List<MensajeEntities>> findAllByIdCliente(Integer idcliente);
}
