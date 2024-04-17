package com.store.dominguez.service.impl;

import com.store.dominguez.dto.TipoDocumentoIdentidadDTO;
import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.repository.gestion.TipoDocumentoIdentidadRepository;
import com.store.dominguez.service.gestion.TipoDocumentoIdentidadService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoIdentidadServiceImpl implements TipoDocumentoIdentidadService {

    @Autowired
    private TipoDocumentoIdentidadRepository tdiRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoDocumentoIdentidadDTO> buscarTodos() {

        try {
            List<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntities = tdiRepository.findAll();
            return tipoDocumentoIdentidadEntities.stream().
                    map(tipoDocumentoIdentidadEntity -> modelMapper.map(tipoDocumentoIdentidadEntity, TipoDocumentoIdentidadDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los tipos de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public List<TipoDocumentoIdentidadDTO> buscarActivo() {
        try {
            List<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntities = tdiRepository.buscarTipoDocumentoIdentidadActivo();
            return tipoDocumentoIdentidadEntities.stream().
                    map(tipoDocumentoIdentidadEntity -> modelMapper.map(tipoDocumentoIdentidadEntity, TipoDocumentoIdentidadDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los tipos de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public List<TipoDocumentoIdentidadDTO> buscarInactivo() {
        try {
            List<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntities = tdiRepository.buscarTipoDocumentoIdentidadInactivo();
            return tipoDocumentoIdentidadEntities.stream().
                    map(tipoDocumentoIdentidadEntity -> modelMapper.map(tipoDocumentoIdentidadEntity, TipoDocumentoIdentidadDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los tipos de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public Optional<TipoDocumentoIdentidadDTO> buscarId(String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede estar vacio");
        try {
            Optional<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntity = tdiRepository.findById(id);
            return tipoDocumentoIdentidadEntity.map(tipoDocumentoIdentidadEntity1 -> modelMapper.map(tipoDocumentoIdentidadEntity1, TipoDocumentoIdentidadDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tipo de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public TipoDocumentoIdentidadDTO agregar(TipoDocumentoIdentidadDTO tipoDocumento) {
        if (Validations.isBlank(tipoDocumento.getNombre())) {
            throw new NullPointerException("El nombre no puede estar vacío");
        }

        try {
            String id = IdGenerator.generarID("TDI", tipoDocumento.getNombre());
            tipoDocumento.setId(id);
            TipoDocumentoIdentidadEntity tipoDocumentoIdentidadEntity = modelMapper.map(tipoDocumento, TipoDocumentoIdentidadEntity.class);
            tipoDocumentoIdentidadEntity = tdiRepository.save(tipoDocumentoIdentidadEntity);
            return modelMapper.map(tipoDocumentoIdentidadEntity, TipoDocumentoIdentidadDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el tipo de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public TipoDocumentoIdentidadDTO actualizar(TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO, String id) {

        if (Validations.isBlank(tipoDocumentoIdentidadDTO.getNombre()))
            throw new NullPointerException("El nombre no puede estar vacio");

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede estar vacio");

        try {

            Optional<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntity = tdiRepository.findById(id);
            if (tipoDocumentoIdentidadEntity.isPresent()) {
                TipoDocumentoIdentidadEntity tipoDocumentoIdentidad = tipoDocumentoIdentidadEntity.get();
                modelMapper.map(tipoDocumentoIdentidadDTO, tipoDocumentoIdentidad);
                tipoDocumentoIdentidad.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(tdiRepository.save(tipoDocumentoIdentidad), TipoDocumentoIdentidadDTO.class);
            } else {
                throw new RuntimeException("El tipo de documento de identidad no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el tipo de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede estar vacio");
        try {
            Optional<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntity = tdiRepository.findById(id);
            if (tipoDocumentoIdentidadEntity.isPresent()) {
                TipoDocumentoIdentidadEntity tipoDocumentoIdentidad = tipoDocumentoIdentidadEntity.get();
                tipoDocumentoIdentidad.setEstado(false);
                tdiRepository.save(tipoDocumentoIdentidad);
            } else {
                throw new RuntimeException("El tipo de documento de identidad no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public TipoDocumentoIdentidadDTO habilitar(String id) {
        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede estar vacio");
        try {
            Optional<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntity = tdiRepository.findById(id);
            if (tipoDocumentoIdentidadEntity.isPresent()) {
                TipoDocumentoIdentidadEntity tipoDocumentoIdentidad = tipoDocumentoIdentidadEntity.get();
                tipoDocumentoIdentidad.setEstado(true);
                return modelMapper.map(tdiRepository.save(tipoDocumentoIdentidad), TipoDocumentoIdentidadDTO.class);
            } else {
                throw new RuntimeException("El tipo de documento de identidad no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el tipo de documento de identidad" + e.getMessage());
        }
    }

    @Override
    public List<TipoDocumentoIdentidadDTO> buscarTipoDocumentoIdentidad(String documento) {
        if (Validations.isBlank(documento)) throw new NullPointerException("El nombre no puede estar vacio");
        try {
            List<TipoDocumentoIdentidadEntity> tipoDocumentoIdentidadEntities = tdiRepository.buscarTipoDocumentoIdentidad(documento);
            return tipoDocumentoIdentidadEntities.stream().
                    map(tipoDocumentoIdentidadEntity -> modelMapper.map(tipoDocumentoIdentidadEntity, TipoDocumentoIdentidadDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el tipo de documento de identidad" + e.getMessage());
        }
    }
}
