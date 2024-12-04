package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.dto.TiposServiciosDTO;
import com.example.cleanmaster.models.entities.TiposServiciosEntities;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiposServiciosRepository extends JpaRepository<TiposServiciosEntities, Integer> {

    @Override
    Optional<TiposServiciosEntities> findById(Integer integer);

    @Override
    List<TiposServiciosEntities> findAll();
}
