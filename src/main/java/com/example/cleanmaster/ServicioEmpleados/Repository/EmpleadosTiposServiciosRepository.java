package com.example.cleanmaster.ServicioEmpleados.Repository;

import com.example.cleanmaster.models.entities.Empleado_tiposServiciosEntities;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpleadosTiposServiciosRepository extends JpaRepository<Empleado_tiposServiciosEntities, Integer> {

}
