package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DetalleGuiaSalidaDTO;
import com.store.dominguez.model.DetalleGuiaSalidaEntity;
import com.store.dominguez.repository.gestion.DetalleGuiaSalidaRepository;
import com.store.dominguez.service.gestion.DetalleGuiaSalidaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DetalleGuiaSalidaServiceImpl implements DetalleGuiaSalidaService {

    private final DetalleGuiaSalidaRepository detalleGuiaSalidaRepository;
    private final ModelMapper modelMapper;

    public DetalleGuiaSalidaServiceImpl(DetalleGuiaSalidaRepository detalleGuiaSalidaRepository, ModelMapper modelMapper) {
        this.detalleGuiaSalidaRepository = detalleGuiaSalidaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DetalleGuiaSalidaDTO> buscarTodos() {
        try {
            List<DetalleGuiaSalidaEntity> detalleGuiaSalidaDTO = detalleGuiaSalidaRepository.findAll();
            return detalleGuiaSalidaDTO.stream().map(detalleGuiaSalida -> modelMapper.map(detalleGuiaSalida, DetalleGuiaSalidaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DetalleGuiaSalidaDTO> buscarActivo() {
        return null;
    }

    @Override
    public List<DetalleGuiaSalidaDTO> buscarInactivo() {
        return null;
    }

    @Override
    public Optional<DetalleGuiaSalidaDTO> buscarId(String id) {

        Optional<DetalleGuiaSalidaEntity> detalleGuiaSalidaEntity = detalleGuiaSalidaRepository.findById(UUID.fromString(id));
        try {
            return detalleGuiaSalidaEntity.map(guiaSalidaEntity -> modelMapper.map(guiaSalidaEntity, DetalleGuiaSalidaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }

    }

    public Optional<DetalleGuiaSalidaDTO> buscarIdDetalleGuiaSalida(UUID id) {
        try {
            Optional<DetalleGuiaSalidaEntity> detalleGuiaSalidaEntity = detalleGuiaSalidaRepository.findByIdDetalleGuiaSalida(id);
            return detalleGuiaSalidaEntity.map(detalleGuiaSalida -> modelMapper.map(detalleGuiaSalida, DetalleGuiaSalidaDTO.class));

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el detalle de la guía de salida" + e.getMessage());
        }
    }


    @Override
    public DetalleGuiaSalidaDTO agregar(DetalleGuiaSalidaDTO detalleGuiaSalidaDTO) {
        return null;
    }

    @Override
    public DetalleGuiaSalidaDTO actualizar(DetalleGuiaSalidaDTO detalleGuiaSalidaDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public DetalleGuiaSalidaDTO habilitar(String id) {
          return null;
    }

    @Override
    public List<DetalleGuiaSalidaDTO> buscarPorGuiaSalida(String guiaSalidaId) {
        return null;
    }

    @Override
    public List<DetalleGuiaSalidaDTO> buscarPorProducto(String productoId) {
        return null;
    }
}
