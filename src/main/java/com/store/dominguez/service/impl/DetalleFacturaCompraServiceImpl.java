package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DetalleFacturaCompraDTO;
import com.store.dominguez.model.DetalleFacturaCompraEntity;
import com.store.dominguez.repository.gestion.DetalleFacturaCompraRepository;
import com.store.dominguez.service.gestion.DetalleFacturaCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleFacturaCompraServiceImpl implements DetalleFacturaCompraService {

    private final DetalleFacturaCompraRepository detalleFacturaCompraRepository;
    private final ModelMapper modelMapper;

    public DetalleFacturaCompraServiceImpl(DetalleFacturaCompraRepository detalleFacturaCompraRepository, ModelMapper modelMapper) {
        this.detalleFacturaCompraRepository = detalleFacturaCompraRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DetalleFacturaCompraDTO> buscarTodos() {
        try {
            List<DetalleFacturaCompraEntity> detallesFacturaCompra = detalleFacturaCompraRepository.findAll();
            return detallesFacturaCompra.stream().map(det -> modelMapper.map(det, DetalleFacturaCompraDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los detalles de la factura de compra: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleFacturaCompraDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<DetalleFacturaCompraDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<DetalleFacturaCompraDTO> buscarId(String id) {
        try {
            Optional<DetalleFacturaCompraEntity> detalleFacturaCompra = detalleFacturaCompraRepository.findById(id);
            return detalleFacturaCompra.map(det -> modelMapper.map(det, DetalleFacturaCompraDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el detalle de la factura de compra: " + e.getMessage());
        }
    }

    @Override
    public DetalleFacturaCompraDTO agregar(DetalleFacturaCompraDTO detalleFacturaCompraDTO) {
        return null;
    }

    @Override
    public DetalleFacturaCompraDTO actualizar(DetalleFacturaCompraDTO detalleFacturaCompraDTO, String id) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public DetalleFacturaCompraDTO habilitar(String id) {
        return null;
    }
}
