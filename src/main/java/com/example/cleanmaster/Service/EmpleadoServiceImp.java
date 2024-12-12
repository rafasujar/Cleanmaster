package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.EmpleadoRepository;
import com.example.cleanmaster.Repository.EmpleadosTiposServiciosRepository;
import com.example.cleanmaster.models.dto.EmpleadoDTO;
import com.example.cleanmaster.models.entities.EmpleadoEntities;
import com.example.cleanmaster.models.entities.Empleado_tiposServiciosEntities;
import com.example.cleanmaster.utils.utilsCleanMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImp implements  EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadosTiposServiciosRepository empleadosTiposServiciosRepository;
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
       Optional< EmpleadoEntities> user = empleadoRepository.findByCorreo(correo);
        if (user.isPresent()) {


        EmpleadoEntities entity = user.get();
        String decodepass = utilsCleanMaster.decodeBase54(entity.getPassword());
            System.out.println("decodepass: [" + decodepass + "]");
            System.out.println("password: [" + password + "]");
        if (decodepass.equals(password)) {
            return EmpleadoDTO.ConvertToDTO(entity);
        }
        }
        return null;
    }

    @Override
    public EmpleadoDTO findById(Integer id) {
       if (id != null){
           return empleadoRepository.findById(id).map(EmpleadoDTO::ConvertToDTO).orElse(null);
       }
        return null;
    }

    @Override
    public EmpleadoDTO existsByCorreo(String correo) {
        Optional<EmpleadoEntities> empleadoEntities = empleadoRepository.findByCorreo(correo);
        if (empleadoEntities.isPresent()){
            return EmpleadoDTO.ConvertToDTO(empleadoEntities.get());
        }
        return null;
    }

    @Override
    public void asignarServicio(Integer idusuario, Integer idservicio) {
        Empleado_tiposServiciosEntities empleado_tiposServiciosEntities = new Empleado_tiposServiciosEntities();
        empleado_tiposServiciosEntities.setIdEmpleado(idusuario);
        empleado_tiposServiciosEntities.setIdTipoServicio(idservicio);
        empleadosTiposServiciosRepository.save(empleado_tiposServiciosEntities);
    }
}
