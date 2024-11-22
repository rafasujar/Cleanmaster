package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.EncargadoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoRepository extends JpaRepository<EncargadoEntities, Integer> {
    boolean existsByIdEmpleados(int id);
}
