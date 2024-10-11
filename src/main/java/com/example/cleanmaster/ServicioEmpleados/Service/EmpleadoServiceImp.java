package com.example.cleanmaster.ServicioEmpleados.Service;

import com.example.cleanmaster.ServicioEmpleados.Repository.EmpleadoRepository;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.models.entities.EmpleadoEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImp implements  EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<EmpleadoDTO> findAll() {
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        for(EmpleadoEntities e : empleadoRepository.findAll()){
            empleadoDTOS.add(EmpleadoDTO.ConvertToDTO(e));
        }
        return empleadoDTOS;
    }

    @Override
    public boolean Guardar(EmpleadoDTO empleadoDTO) {
        empleadoRepository.save(EmpleadoDTO.ConvertToEntities(empleadoDTO));
        return empleadoRepository.existsById(empleadoDTO.getId());
    }

    @Override
    public boolean eliminarEmpleado(Integer id) {
        empleadoRepository.deleteById(id);

        return empleadoRepository.existsById(id);
    }

    @Override
    public void update(EmpleadoDTO empleadoDTO, int id) {
        empleadoRepository.save(EmpleadoDTO.ConvertToEntities(empleadoDTO));
    }

    @Override
    public EmpleadoDTO logearEmpleado(String correo, String password) {
        Optional<EmpleadoEntities> empleadoEntities =  empleadoRepository.findByCorreoAndPassword(correo, password);
        if(empleadoEntities.isPresent()){
            return EmpleadoDTO.ConvertToDTO(empleadoEntities.get());
        }
        return null;
    }
}
