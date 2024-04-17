package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DetalleOrdenCompraDTO;
import com.store.dominguez.model.DetalleOrdenCompraEntity;
import com.store.dominguez.repository.gestion.DetalleOrdenCompraRepository;
import com.store.dominguez.service.gestion.DetalleOrdenCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenCompraServiceImpl implements DetalleOrdenCompraService {

    private final ModelMapper modelMapper;
    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;


    public DetalleOrdenCompraServiceImpl(ModelMapper modelMapper, DetalleOrdenCompraRepository detalleOrdenCompraRepository) {
        this.modelMapper = modelMapper;
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
    }

    @Override
    public List<DetalleOrdenCompraDTO> buscarTodos() {
        try {
            List<DetalleOrdenCompraEntity> list = detalleOrdenCompraRepository.findAll();
            return list.stream().map(cat -> modelMapper.map(cat, DetalleOrdenCompraDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los detalles de la orden de compra" + e.getMessage());
        }
    }

    @Override
    public List<DetalleOrdenCompraDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<DetalleOrdenCompraDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<DetalleOrdenCompraDTO> buscarId(String id) {

        try {
            Optional<DetalleOrdenCompraEntity> detalleOrdenCompraEntity = detalleOrdenCompraRepository.findById(UUID.fromString(id));
            return detalleOrdenCompraEntity.map(value -> modelMapper.map(value, DetalleOrdenCompraDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el detalle de la orden de compra" + e.getMessage());
        }
    }

    @Override
    public DetalleOrdenCompraDTO agregar(DetalleOrdenCompraDTO detalleOrdenCompraDTO) {
        return null;
    }

    @Override
    public DetalleOrdenCompraDTO actualizar(DetalleOrdenCompraDTO detalleOrdenCompraDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public DetalleOrdenCompraDTO habilitar(String id) {
        return null;
    }
}
