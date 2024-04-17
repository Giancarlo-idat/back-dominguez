package com.store.dominguez.service.impl;

import com.store.dominguez.dto.RolDTO;
import com.store.dominguez.model.RolEntity;
import com.store.dominguez.repository.gestion.RolRepository;
import com.store.dominguez.service.gestion.RolService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.RolValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    private final RolValidator rolValidator;

    public RolServiceImpl(RolRepository rolRepository, ModelMapper modelMapper, RolValidator rolValidator) {
        this.rolRepository = rolRepository;
        this.modelMapper = modelMapper;
        this.rolValidator = rolValidator;
    }

    @Override
    public List<RolDTO> buscarTodos() {
        try {
            List<RolEntity> roles = rolRepository.findAll();
            return roles.stream().map(rol -> modelMapper.map(rol, RolDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el rol" + e.getMessage());
        }
    }

    @Override
    public List<RolDTO> buscarActivo() {
        try {
            List<RolEntity> roles = rolRepository.buscarRolActivo();
            return roles.stream().map(rol -> modelMapper.map(rol, RolDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el rol" + e.getMessage());
        }
    }

    @Override
    public List<RolDTO> buscarInactivo() {
        try {
            List<RolEntity> roles = rolRepository.buscarRolInactivo();
            return roles.stream().map(rol -> modelMapper.map(rol, RolDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el rol" + e.getMessage());
        }
    }

    @Override
    public Optional<RolDTO> buscarId(String id) {

        RolValidator.validarId(id);

        try {
            Optional<RolEntity> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                return Optional.of(modelMapper.map(rol.get(), RolDTO.class));
            } else {
                throw new RuntimeException("El rol no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el rol" + e.getMessage());
        }
    }

    @Override
    public List<RolDTO> buscarRoles(String roles) {
        if (roles.isEmpty()) throw new IllegalArgumentException("Debes ingresar un dato");
        try {
            List<RolEntity> listaRoles = rolRepository.buscarRol(roles);
            return listaRoles.stream()
                    .map(rol -> modelMapper.map(rol, RolDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los datos del rol: " + e.getMessage());
        }
    }

    @Override
    public RolDTO agregar(RolDTO rolDTO) {

        rolValidator.validarRoles(rolDTO);

        try {
            String id = IdGenerator.generarID("ROL", (rolDTO.getNombre().trim()));
            rolDTO.setId(id);
            RolEntity rolEntity = modelMapper.map(rolDTO, RolEntity.class);
            rolEntity = rolRepository.save(rolEntity);
            return modelMapper.map(rolEntity, RolDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el rol" + e.getMessage());
        }
    }


    @Override
    public RolDTO actualizar(RolDTO rolDTO, String id) {
        RolValidator.validarId(id);
        try {
            Optional<RolEntity> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                RolValidator.validarNombreRol(rolDTO.getNombre());
                RolEntity rolEntity = rol.get();
                modelMapper.map(rolDTO, rolEntity);
                rolEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(rolRepository.save(rolEntity), RolDTO.class);
            } else {
                throw new RuntimeException("El rol no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el rol" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        RolValidator.validarId(id);
        try {
            Optional<RolEntity> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                RolEntity rolEntity = rol.get();
                rolEntity.setEstado(false);
                rolEntity.setFechaActualizacion(LocalDateTime.now());
                rolRepository.save(rolEntity);
            } else {
                throw new RuntimeException("El rol no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el rol" + e.getMessage());
        }

    }

    @Override
    public RolDTO habilitar(String id) {
        RolValidator.validarId(id);

        try {
            Optional<RolEntity> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                RolEntity rolEntity = rol.get();
                rolEntity.setEstado(true);
                rolEntity.setFechaActualizacion(LocalDateTime.now());
                rolEntity = rolRepository.save(rolEntity);
                return modelMapper.map(rolEntity, RolDTO.class);
            } else {
                throw new RuntimeException("El rol no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el rol" + e.getMessage());
        }

    }

}
