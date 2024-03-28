package com.store.dominguez.service.impl;

import com.store.dominguez.dto.GuiaSalidaDTO;
import com.store.dominguez.model.GuiaSalidaEntity;
import com.store.dominguez.repository.gestion.GuiaSalidaRepository;
import com.store.dominguez.service.gestion.GuiaSalidaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuiaSalidaServiceImpl implements GuiaSalidaService {

    private final GuiaSalidaRepository guiaSalidaRepository;
    private final ModelMapper modelMapper;

    public GuiaSalidaServiceImpl(GuiaSalidaRepository guiaSalidaRepository, ModelMapper modelMapper) {
        this.guiaSalidaRepository = guiaSalidaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GuiaSalidaDTO> buscarTodos() {
        try {
            List<GuiaSalidaEntity> guiaSalidaEntities = guiaSalidaRepository.findAll();
            return guiaSalidaEntities.stream()
                    .map(guiaSalidaEntity -> modelMapper.map(guiaSalidaEntity, GuiaSalidaDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<GuiaSalidaDTO> buscarActivo() {
        return null;
    }

    @Override
    public List<GuiaSalidaDTO> buscarInactivo() {
        return null;
    }

    @Override
    public Optional<GuiaSalidaDTO> buscarId(String id) {
        return Optional.empty();
    }

    @Override
    public GuiaSalidaDTO agregar(GuiaSalidaDTO guiaSalidaDTO) {
        return null;
    }

    @Override
    public GuiaSalidaDTO actualizar(GuiaSalidaDTO guiaSalidaDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public GuiaSalidaDTO habilitar(String id) {
        return null;
    }

    @Override
    public List<GuiaSalidaDTO> buscarPorCliente(Long clienteId) {
        return null;
    }

    @Override
    public List<GuiaSalidaDTO> buscarPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }
}
