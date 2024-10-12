package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.EmpleadoRepository;
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
    public void Guardar(EmpleadoDTO empleadoDTO) {
        empleadoRepository.save(EmpleadoDTO.ConvertToEntities(empleadoDTO));
    }

    @Override
    public void eliminarEmpleado(Integer id) {
        empleadoRepository.deleteById(id);

    }

    @Override
    public void update(EmpleadoDTO empleadoDTO) {
        //empleadoRepository.updateById(empleadoDTO);
    }

    @Override
    public EmpleadoDTO logearEmpleado(String correo, String password) {
        Optional<EmpleadoEntities> empleadoEntities =  empleadoRepository.findByCorreoAndPassword(correo, password);
        return empleadoEntities.map(EmpleadoDTO::ConvertToDTO).orElse(null);
    }

    @Override
    public EmpleadoDTO existsByCorreo(String correo) {
        Optional<EmpleadoEntities> empleadoEntities = empleadoRepository.findByCorreo(correo);
        if (empleadoEntities.isPresent()){
            return EmpleadoDTO.ConvertToDTO(empleadoEntities.get());
        }
        return null;
    }
}
