package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.Empleado_tiposServiciosEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosTiposServiciosRepository extends JpaRepository<Empleado_tiposServiciosEntities, Integer> {

}
