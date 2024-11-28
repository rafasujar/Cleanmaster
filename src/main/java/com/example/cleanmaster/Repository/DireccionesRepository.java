package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.DireccionesEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepository extends JpaRepository<DireccionesEntities, Integer> {
    DireccionesEntities getDireccionesEntitiesById(Integer id);
}
