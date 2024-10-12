package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.MensajeEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajesRepository extends JpaRepository<MensajeEntities, Integer> {
}
