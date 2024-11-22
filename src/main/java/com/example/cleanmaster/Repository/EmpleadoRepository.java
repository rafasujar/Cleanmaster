package com.example.cleanmaster.Repository;


import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.models.entities.EmpleadoEntities;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntities, Integer> {
    Optional<EmpleadoEntities> findByCorreo(String correo);

    Optional<EmpleadoEntities> findByCorreoAndPassword(String correo, String password);


}
