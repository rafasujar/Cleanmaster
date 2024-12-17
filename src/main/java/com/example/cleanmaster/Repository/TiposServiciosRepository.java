package com.example.cleanmaster.Repository;

import com.example.cleanmaster.models.entities.TiposServiciosEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiposServiciosRepository extends JpaRepository<TiposServiciosEntities, Integer> {

    @Override
    Optional<TiposServiciosEntities> findById(Integer id);

    @Override
    List<TiposServiciosEntities> findAll();

    Optional<TiposServiciosEntities> getTiposServiciosEntitiesById(int id);

    @Query("select t from TiposServiciosEntities t , Empleado_tiposServiciosEntities e where t.id = e.idTipoServicio and e.idEmpleado = :idEmpleado")
    List<TiposServiciosEntities> getTiposServiciosEntitiesByEmpleadosId(Integer idEmpleado);
}
