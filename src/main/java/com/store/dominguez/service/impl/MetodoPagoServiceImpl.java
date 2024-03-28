package com.store.dominguez.service.impl;


import com.store.dominguez.dto.MetodoPagoDTO;
import com.store.dominguez.model.MetodoPagoEntity;
import com.store.dominguez.repository.gestion.MetodoPagoRepository;
import com.store.dominguez.service.gestion.MetodoPagoService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.MetodoPagosValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {
    private final MetodoPagoRepository metodoPagoRepository;
    private final MetodoPagosValidator metodoPagoValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MetodoPagoServiceImpl(MetodoPagoRepository metodoPagoRepository, MetodoPagosValidator metodoPagoValidator, ModelMapper modelMapper) {
        this.metodoPagoRepository = metodoPagoRepository;
        this.metodoPagoValidator = metodoPagoValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public MetodoPagoDTO findMetodoPagoByNombre(String nombre) {
        if (Validations.isBlank(nombre))
            throw new RuntimeException("El nombre del método de pago no puede estar vacío");

        try {
            MetodoPagoEntity metodoPago = metodoPagoRepository.findMetodoPagoByNombre(nombre);
            // Verificar si el nombre del metodo de pago existe
            if (metodoPago == null) throw new RuntimeException("El método de pago no existe");
            return modelMapper.map(metodoPago, MetodoPagoDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el método de pago" + e.getMessage());
        }
    }

    @Override
    public List<MetodoPagoDTO> buscarTodos() {
        try {
            List<MetodoPagoEntity> list = metodoPagoRepository.findAll();
            return list.stream().map(met -> modelMapper.map(met, MetodoPagoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el método de pago" + e.getMessage());
        }
    }

    @Override
    public List<MetodoPagoDTO> buscarActivo() {
        try {
            List<MetodoPagoEntity> list = metodoPagoRepository.buscarMetodoPagoActivo();
            return list.stream().map(met -> modelMapper.map(met, MetodoPagoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el método de pago" + e.getMessage());
        }
    }

    @Override
    public List<MetodoPagoDTO> buscarInactivo() {
        try {
            List<MetodoPagoEntity> list = metodoPagoRepository.buscarMetodoPagoInactivo();
            return list.stream().map(met -> modelMapper.map(met, MetodoPagoDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el método de pago" + e.getMessage());
        }
    }

    @Override
    public Optional<MetodoPagoDTO> buscarId(String id) {
        MetodoPagosValidator.validarIdMetodoPago(id);

        try {
            Optional<MetodoPagoEntity> metodoPago = metodoPagoRepository.findById(id);
            if (metodoPago.isPresent()) {
                return Optional.of(modelMapper.map(metodoPago.get(), MetodoPagoDTO.class));
            } else {
                throw new RuntimeException("El método de pago no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el método de pago" + e.getMessage());
        }
    }

    @Override
    public MetodoPagoDTO agregar(MetodoPagoDTO metodoPagoDTO) {
        metodoPagoValidator.validarMetodoPago(metodoPagoDTO);
        try {
            String id = IdGenerator.generarID("MTDP", metodoPagoDTO.getNombre());
            metodoPagoDTO.setId(id);
            MetodoPagoEntity metodoPagoEntity = modelMapper.map(metodoPagoDTO, MetodoPagoEntity.class);
            metodoPagoEntity = metodoPagoRepository.save(metodoPagoEntity);
            return modelMapper.map(metodoPagoEntity, MetodoPagoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el método de pago" + e.getMessage());
        }
    }

    @Override
    public MetodoPagoDTO actualizar(MetodoPagoDTO metodoPagoDTO, String id) {
        MetodoPagosValidator.validarIdMetodoPago(id);
        try {
            Optional<MetodoPagoEntity> metodoPagoEntity = metodoPagoRepository.findById(id);
            if (metodoPagoEntity.isPresent()) {
                // Valida que el campo nombre no esté vacío
                MetodoPagosValidator.validarNombreMetodoPago(metodoPagoDTO.getNombre());
                MetodoPagoEntity metodoPago = metodoPagoEntity.get();
                modelMapper.map(metodoPagoDTO, metodoPago);
                metodoPago.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(metodoPagoRepository.save(metodoPago), MetodoPagoDTO.class);
            } else {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoria" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        MetodoPagosValidator.validarIdMetodoPago(id);
        try {
            Optional<MetodoPagoEntity> metodoPagoEntity = metodoPagoRepository.findById(id);
            if (metodoPagoEntity.isPresent()) {
                MetodoPagoEntity metodoPago = metodoPagoEntity.get();
                metodoPago.setEstado(false);
                metodoPago.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(metodoPagoRepository.save(metodoPago), MetodoPagoDTO.class);
            } else {
                throw new IllegalArgumentException("El metodo de pago no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar el método de pago" + e.getMessage());
        }
    }

    @Override
    public MetodoPagoDTO habilitar(String id) {
        MetodoPagosValidator.validarIdMetodoPago(id);
        try {
            Optional<MetodoPagoEntity> metodoPagoEntity = metodoPagoRepository.findById(id);
            if (metodoPagoEntity.isPresent()) {
                MetodoPagoEntity metodoPago = metodoPagoEntity.get();
                metodoPago.setEstado(true);
                metodoPago.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(metodoPagoRepository.save(metodoPago), MetodoPagoDTO.class);
            } else {
                throw new IllegalArgumentException("El metodo de pago no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el método de pago" + e.getMessage());
        }
    }
}
