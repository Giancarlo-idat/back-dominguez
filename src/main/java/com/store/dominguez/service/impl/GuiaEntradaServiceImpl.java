package com.store.dominguez.service.impl;

import com.store.dominguez.dto.GuiaEntradaDTO;
import com.store.dominguez.model.GuiaEntradaEntity;
import com.store.dominguez.repository.gestion.GuiaEntradaRepository;
import com.store.dominguez.service.gestion.GuiaEntradaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuiaEntradaServiceImpl implements GuiaEntradaService {

    private final GuiaEntradaRepository guiaEntradaRepository;
    private final ModelMapper modelMapper;

    public GuiaEntradaServiceImpl(GuiaEntradaRepository guiaEntradaRepository, ModelMapper modelMapper) {
        this.guiaEntradaRepository = guiaEntradaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GuiaEntradaDTO> buscarTodos() {
        try {
            List<GuiaEntradaEntity> guiaEntradaEntity = guiaEntradaRepository.findAll();
            return guiaEntradaEntity.stream().map(guiaEntrada -> modelMapper.map(guiaEntrada, GuiaEntradaDTO.class)).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todas las guias de entrada", e);
        }
    }

    @Override
    public List<GuiaEntradaDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<GuiaEntradaDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<GuiaEntradaDTO> buscarId(String id) {
        try {
            Optional<GuiaEntradaEntity> guiaEntradaEntity = guiaEntradaRepository.findById(id);
            return guiaEntradaEntity.map(guiaEntrada -> modelMapper.map(guiaEntrada, GuiaEntradaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la guia de entrada por id", e);
        }
    }

    @Override
    public GuiaEntradaDTO agregar(GuiaEntradaDTO guiaEntradaDTO) {
        return null;
    }

    @Override
    public GuiaEntradaDTO actualizar(GuiaEntradaDTO guiaEntradaDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public GuiaEntradaDTO habilitar(String id) {
        return null;
    }
}
