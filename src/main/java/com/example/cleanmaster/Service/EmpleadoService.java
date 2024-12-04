package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.EmpleadoDTO;

import java.util.List;

public interface EmpleadoService {
    public List<EmpleadoDTO> findAll();
    public void Guardar(EmpleadoDTO empleadoDTO);
    public void eliminarEmpleado(Integer id);
    public void update(EmpleadoDTO empleadoDTO);
    public EmpleadoDTO logearEmpleado(String correo, String password);
    EmpleadoDTO findById(Integer id);
    EmpleadoDTO existsByCorreo(String correo);

}
