package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DetalleGuiaEntradaDTO;
import com.store.dominguez.model.DetalleGuiaEntradaEntity;
import com.store.dominguez.repository.gestion.DetalleGuiaEntradaRepository;
import com.store.dominguez.service.gestion.DetalleGuiaEntradaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DetalleGuiaEntradaServiceImpl implements DetalleGuiaEntradaService {

    private final DetalleGuiaEntradaRepository detalleGuiaEntradaRepository;

    private final ModelMapper modelMapper;

    public DetalleGuiaEntradaServiceImpl(DetalleGuiaEntradaRepository detalleGuiaEntradaRepository, ModelMapper modelMapper) {
        this.detalleGuiaEntradaRepository = detalleGuiaEntradaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DetalleGuiaEntradaDTO> buscarTodos() {
        try {
            List<DetalleGuiaEntradaEntity> detalleGuiaEntradaEntity = detalleGuiaEntradaRepository.findAll();
            return List.of(modelMapper.map(detalleGuiaEntradaEntity, DetalleGuiaEntradaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los detalles de guia de entrada");
        }
    }

    @Override
    public List<DetalleGuiaEntradaDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<DetalleGuiaEntradaDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<DetalleGuiaEntradaDTO> buscarId(String id) {
        try {
            Optional<DetalleGuiaEntradaEntity> detalleGuiaEntradaEntity = detalleGuiaEntradaRepository.findById(UUID.fromString(id));
            return detalleGuiaEntradaEntity.map(entity -> modelMapper.map(entity, DetalleGuiaEntradaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el detalle de guia de entrada");
        }
    }

    @Override
    public DetalleGuiaEntradaDTO agregar(DetalleGuiaEntradaDTO detalleGuiaEntradaDTO) {
        return null;
    }

    @Override
    public DetalleGuiaEntradaDTO actualizar(DetalleGuiaEntradaDTO detalleGuiaEntradaDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public DetalleGuiaEntradaDTO habilitar(String id) {
        return null;
    }
}
