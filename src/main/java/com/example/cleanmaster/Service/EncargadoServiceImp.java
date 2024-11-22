package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.EncargadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncargadoServiceImp implements EncargadoService {
    @Autowired
    private EncargadoRepository encargadoRepository;
    @Override
    public boolean esEncargado(int id) {
        return encargadoRepository.existsByIdEmpleados(id);
    }
}
