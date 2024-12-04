package com.example.cleanmaster.Repository;


import com.example.cleanmaster.models.entities.ClienteEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntities, Integer> {


    Optional<ClienteEntities> findByCorreoAndPassword( String correo, String password);

    Optional<ClienteEntities> findByCorreo(String correo);

    Optional<ClienteEntities> findById(Integer id);
}
