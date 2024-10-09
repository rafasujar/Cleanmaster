package com.example.cleanmaster.ServicioEmpleados.Service;

import com.example.cleanmaster.models.dto.EmpleadoDTO;

import java.util.List;

public interface EmpleadoService {
    public List<EmpleadoDTO> findAll();
    public boolean Guardar(EmpleadoDTO empleadoDTO);
    public boolean eliminarEmpleado(Integer id);
    public void update(EmpleadoDTO empleadoDTO, int id);
    public EmpleadoDTO logearEmpleado(String correo, String password);

}
