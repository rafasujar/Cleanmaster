package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.dto.DireccionesDTO;
import com.example.cleanmaster.models.entities.DireccionesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionesRepository extends JpaRepository<DireccionesEntities, Integer> {
    DireccionesEntities getDireccionesEntitiesById(Integer id);

    List<DireccionesEntities> findAllByIdCliente(Integer id);


}
