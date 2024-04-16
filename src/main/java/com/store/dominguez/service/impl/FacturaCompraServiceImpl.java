package com.store.dominguez.service.impl;

import com.store.dominguez.dto.FacturaCompraDTO;
import com.store.dominguez.model.FacturaCompraEntity;
import com.store.dominguez.repository.gestion.FacturaCompraRepository;
import com.store.dominguez.service.gestion.FacturaCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaCompraServiceImpl implements FacturaCompraService {

    public final FacturaCompraRepository facturaCompraRepository;
    public final ModelMapper modelMapper;

    public FacturaCompraServiceImpl(FacturaCompraRepository facturaCompraRepository, ModelMapper modelMapper) {
        this.facturaCompraRepository = facturaCompraRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FacturaCompraDTO> buscarTodos() {
        try {
            List<FacturaCompraEntity> facturaCompraDTO = facturaCompraRepository.findAll();
            return facturaCompraDTO.stream().map(facturaCompra -> modelMapper.map(facturaCompra, FacturaCompraDTO.class)).toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todas las facturas de compra", e);
        }
    }

    @Override
    public List<FacturaCompraDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<FacturaCompraDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<FacturaCompraDTO> buscarId(String id) {
        try {
            Optional<FacturaCompraEntity> facturaCompraEntity = facturaCompraRepository.findById(id);
            return facturaCompraEntity.map(facturaCompra -> modelMapper.map(facturaCompra, FacturaCompraDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la factura de compra por id", e);
        }
    }

    @Override
    public FacturaCompraDTO agregar(FacturaCompraDTO facturaCompraDTO) {
        return null;
    }

    @Override
    public FacturaCompraDTO actualizar(FacturaCompraDTO facturaCompraDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public FacturaCompraDTO habilitar(String id) {
        return null;
    }
}
