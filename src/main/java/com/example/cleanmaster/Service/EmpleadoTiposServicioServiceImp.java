package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.EmpleadosTiposServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoTiposServicioServiceImp implements EmpleadoTiposServicioService {
    @Autowired
    private EmpleadosTiposServiciosRepository empleadosTiposServiciosRepository;

}
