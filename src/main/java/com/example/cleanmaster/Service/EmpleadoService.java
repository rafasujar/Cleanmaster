package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.EmpleadoDTO;

import java.util.List;


public interface EmpleadoService {
    List<EmpleadoDTO> findAll();

    void Guardar(EmpleadoDTO empleadoDTO);

    void eliminarEmpleado(Integer id);

    void update(EmpleadoDTO empleadoDTO);

    EmpleadoDTO logearEmpleado(String correo, String password);

    EmpleadoDTO findById(Integer id);

    EmpleadoDTO existsByCorreo(String correo);

    void asignarServicio(Integer id, Integer id1);

    List<EmpleadoDTO> findAllByIdEncargado(Integer id);
}
