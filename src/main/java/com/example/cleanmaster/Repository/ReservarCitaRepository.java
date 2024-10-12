package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.ReservarCitaEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservarCitaRepository extends JpaRepository<ReservarCitaEntities, Integer> {
}
