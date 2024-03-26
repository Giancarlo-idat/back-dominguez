package com.store.dominguez.service.impl;


import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.model.EmpleadoEntity;
import com.store.dominguez.repository.gestion.EmpleadoRepository;
import com.store.dominguez.service.gestion.EmpleadoService;
import com.store.dominguez.util.validations.EmpleadoValidator;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final ModelMapper modelMapper;
    private final EmpleadoValidator empleadoValidator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, ModelMapper modelMapper, EmpleadoValidator empleadoValidator, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.modelMapper = modelMapper;
        this.empleadoValidator = empleadoValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<EmpleadoDTO> buscarTodos() {
        try {
            List<EmpleadoEntity> list = empleadoRepository.findAll();
            return list.stream().map(cat -> modelMapper.map(cat, EmpleadoDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado: " + e.getMessage());
        }
    }

    @Override
    public List<EmpleadoDTO> buscarActivo() {
        try {
            List<EmpleadoEntity> list = empleadoRepository.buscarEmpleadoActivo();
            return list.stream().map(cat -> modelMapper.map(cat, EmpleadoDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado: " + e.getMessage());
        }
    }

    @Override
    public List<EmpleadoDTO> buscarInactivo() {
        try {
            List<EmpleadoEntity> list = empleadoRepository.buscarEmpleadoInactivo();
            return list.stream().map(cat -> modelMapper.map(cat, EmpleadoDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado: " + e.getMessage());
        }
    }

    @Override
    public Optional<EmpleadoDTO> buscarId(String id) {
        EmpleadoValidator.validarId(id);

        try {
            Optional<EmpleadoEntity> empleadoEntity = empleadoRepository.findById(id);
            if (empleadoEntity.isPresent()) {
                return Optional.of(modelMapper.map(empleadoEntity.get(), EmpleadoDTO.class));
            } else {
                throw new RuntimeException("El empleado no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado: " + e.getMessage());
        }
    }

    @Override
    public EmpleadoDTO agregar(EmpleadoDTO empleadoDTO) {

        empleadoValidator.validarEmpleado(empleadoDTO);

        try {
            String id = IdGenerator.generarID("EMP", (empleadoDTO.getNombres().trim() + empleadoDTO.getApellidos()));
            empleadoDTO.setId(id);
            empleadoDTO.setPassword(passwordEncoder.encode(empleadoDTO.getPassword()));
            EmpleadoEntity empleado = modelMapper.map(empleadoDTO, EmpleadoEntity.class);
            empleado = empleadoRepository.save(empleado);
            return modelMapper.map(empleado, EmpleadoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el empleado: " + e.getMessage());
        }
    }


    @Override
    public EmpleadoDTO actualizar(EmpleadoDTO empleadoDTO, String id) {

        EmpleadoValidator.validarId(id);
        try {
            Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
            if (empleado.isPresent()) {
                empleadoValidator.validarEmpleado(empleadoDTO);
                EmpleadoEntity empleadoEntity = empleado.get();
                modelMapper.map(empleadoDTO, empleadoEntity);
                empleadoEntity.setPassword(passwordEncoder.encode(empleadoDTO.getPassword()));
                empleadoEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(empleadoRepository.save(empleadoEntity), EmpleadoDTO.class);
            } else {
                throw new RuntimeException("El empleado no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage());
        }

    }

    @Override
    public void eliminar(String id) {

        EmpleadoValidator.validarId(id);

        try {
            Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
            if (empleado.isPresent()) {
                EmpleadoEntity empleadoEntity = empleado.get();
                empleadoEntity.setEstado(false);
                empleadoEntity.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(empleadoRepository.save(empleadoEntity), EmpleadoDTO.class);
            } else {
                throw new RuntimeException("El empleado no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el empleado: " + e.getMessage());
        }
    }

    @Override
    public EmpleadoDTO habilitar(String id) {
        EmpleadoValidator.validarId(id);
        try {
            Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
            if (empleado.isPresent()) {
                EmpleadoEntity empleadoEntity = empleado.get();
                empleadoEntity.setEstado(true);
                empleadoEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(empleadoRepository.save(empleadoEntity), EmpleadoDTO.class);
            } else {
                throw new RuntimeException("El empleado no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el empleado: " + e.getMessage());
        }
    }

    @Override
    public List<EmpleadoDTO> buscarDatosEmpleado(String datos) {

        if (Validations.isBlank(datos)) throw new IllegalArgumentException("Los datos no pueden ser vacíos");
        try {
            List<EmpleadoEntity> list = empleadoRepository.buscarDatosEmpleado(datos);
            return list.stream().map(cat -> modelMapper.map(cat, EmpleadoDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el empleado" + e.getMessage());
        }

    }

}
