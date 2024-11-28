package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.dto.TiposServiciosDTO;
import com.example.cleanmaster.models.entities.TiposServiciosEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposServiciosRepository extends JpaRepository<TiposServiciosEntities, Integer> {

    TiposServiciosDTO getTiposServiciosEntitiesById(Integer id);
}
