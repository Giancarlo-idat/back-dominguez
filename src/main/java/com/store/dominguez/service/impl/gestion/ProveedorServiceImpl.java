package com.store.dominguez.service.impl.gestion;


import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.model.ProveedorEntity;
import com.store.dominguez.repository.gestion.ProveedorRepository;
import com.store.dominguez.service.gestion.ProveedorService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.ProveedorValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ModelMapper modelMapper;
    private final ProveedorValidator proveedorValidator;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository, ModelMapper modelMapper, ProveedorValidator proveedorValidator) {
        this.proveedorRepository = proveedorRepository;
        this.modelMapper = modelMapper;
        this.proveedorValidator = proveedorValidator;
    }

    @Override
    public List<ProveedorDTO> buscarTodos() {
        try {
            List<ProveedorEntity> list = proveedorRepository.findAll();
            return list.stream().map(cat -> modelMapper.map(cat, ProveedorDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor" + e.getMessage());
        }
    }

    @Override
    public List<ProveedorDTO> buscarActivo() {
        try {
            List<ProveedorEntity> list = proveedorRepository.buscarProveedorActivo();
            return list.stream().map(cat -> modelMapper.map(cat, ProveedorDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor" + e.getMessage());
        }
    }

    @Override
    public List<ProveedorDTO> buscarInactivo() {
        try {
            List<ProveedorEntity> list = proveedorRepository.buscarProveedorInactivo();
            return list.stream().map(cat -> modelMapper.map(cat, ProveedorDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor" + e.getMessage());
        }
    }

    @Override
    public Optional<ProveedorDTO> buscarId(String id) {

        // Valida que el campo no esté vacío.
        ProveedorValidator.validarCampoIdProveedor(id);
        try {
            Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                return Optional.of(modelMapper.map(proveedor.get(), ProveedorDTO.class));
            } else {
                throw new RuntimeException("No se encontró el proveedor con el id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor" + e.getMessage());
        }
    }

    @Override
    public List<ProveedorDTO> agregar(List<ProveedorDTO> proveedoresDTO) {

        List<ProveedorDTO> proveedoresAgregados = new ArrayList<>();
        for (ProveedorDTO proveedorDTO : proveedoresDTO) {
            proveedorValidator.validarProveedor(proveedorDTO);
            try {
                String idProveedor = IdGenerator.generarID("PROV", (proveedorDTO.getNombres() + proveedorDTO.getEmail()));
                proveedorDTO.setId(idProveedor);
                ProveedorEntity proveedor = modelMapper.map(proveedorDTO, ProveedorEntity.class);
                proveedor = proveedorRepository.save(proveedor);

                proveedoresAgregados.add(modelMapper.map(proveedor, ProveedorDTO.class));
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar el proveedor" + e.getMessage());
            }
        }

        return proveedoresAgregados;
    }

    @Override
    public ProveedorDTO actualizar(ProveedorDTO proveedorDTO, String id) {

        // Valida que el campo no esté vacío.
        ProveedorValidator.validarCampoIdProveedor(id);
        try {
            Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                proveedorValidator.validarProveedor(proveedorDTO);
                ProveedorEntity proveedorEntity = proveedor.get();
                modelMapper.map(proveedorDTO, proveedorEntity);
                proveedorEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(proveedorRepository.save(proveedorEntity), ProveedorDTO.class);
            } else {
                throw new RuntimeException("No se encontró el proveedor con el id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el proveedor" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {

        // Valida que el campo no esté vacío.
        ProveedorValidator.validarCampoIdProveedor(id);
        try {
            Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                ProveedorEntity proveedorEntity = proveedor.get();
                proveedorEntity.setEstado(false);
                modelMapper.map(proveedorRepository.save(proveedorEntity), ProveedorDTO.class);
            } else {
                throw new RuntimeException("No se encontró el proveedor con el id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el proveedor" + e.getMessage());
        }
    }

    @Override
    public ProveedorDTO habilitar(String id) {

        // Valida que el campo no esté vacío.
        ProveedorValidator.validarCampoIdProveedor(id);
        try {
            Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                ProveedorEntity proveedorEntity = proveedor.get();
                proveedorEntity.setEstado(true);
                proveedorEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(proveedorRepository.save(proveedorEntity), ProveedorDTO.class);
            } else {
                throw new RuntimeException("No se encontró el proveedor con el id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el proveedor" + e.getMessage());
        }
    }

    @Override
    public List<ProveedorDTO> buscarDatosProveedor(String datos) {

        if (Validations.isBlank(datos)) throw new NullPointerException("Los campos a buscar no puede ser nulo");
        try {
            List<ProveedorEntity> list = proveedorRepository.buscarDatosProveedor(datos);
            return list.stream()
                    .map(cat -> modelMapper.map(cat, ProveedorDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el proveedor" + e.getMessage());
        }
    }
}
