package com.example.cleanmaster.ServicioEmpleados.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntities, Integer> {
    EmpleadoEntities findByCorreo(String correo);

    EmpleadoEntities findByCorreoAndPassword(String correo, String password);
}
