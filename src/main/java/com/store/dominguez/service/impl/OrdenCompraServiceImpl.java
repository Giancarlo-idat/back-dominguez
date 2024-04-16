package com.store.dominguez.service.impl;


import com.store.dominguez.dto.DetalleOrdenCompraDTO;
import com.store.dominguez.dto.OrdenCompraDTO;
import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.model.*;
import com.store.dominguez.model.enums.EstadoFactura;
import com.store.dominguez.model.enums.EstadoOrden;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.service.gestion.OrdenCompraService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.generator.NumeroCorrelativoGenerator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;
    private final DetalleGuiaEntradaRepository detalleGuiaEntradaRepository;
    private final GuiaEntradaRepository guiaEntradaRepository;
    private final ModelMapper modelMapper;
    private final FacturaCompraRepository facturaCompraRepository;
    private final DetalleFacturaCompraRepository detalleFacturaCompraRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;

    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository, DetalleOrdenCompraRepository detalleOrdenCompraRepository, DetalleGuiaEntradaRepository detalleGuiaEntradaRepository, GuiaEntradaRepository guiaEntradaRepository, ModelMapper modelMapper, FacturaCompraRepository facturaCompraRepository, DetalleFacturaCompraRepository detalleFacturaCompraRepository, ProveedorRepository proveedorRepository, ProductoRepository productoRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
        this.detalleGuiaEntradaRepository = detalleGuiaEntradaRepository;
        this.guiaEntradaRepository = guiaEntradaRepository;
        this.modelMapper = modelMapper;
        this.facturaCompraRepository = facturaCompraRepository;
        this.detalleFacturaCompraRepository = detalleFacturaCompraRepository;
        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
    }


    @Override
    public List<OrdenCompraDTO> buscarTodos() {
        try {
            List<OrdenCompraEntity> list = ordenCompraRepository.findAll();
            return list.stream()
                    .map(ordenCompra -> {
                        OrdenCompraDTO ordenCompraDTO = new OrdenCompraDTO();
                        ProveedorEntity proveedor = ordenCompra.getProveedor();
                        ProveedorDTO proveedorDTO = new ProveedorDTO();

                        ordenCompraDTO.setId(ordenCompra.getId());
                        ordenCompraDTO.setProveedor(modelMapper.map(ordenCompra.getProveedor(), ProveedorDTO.class));
                        ordenCompraDTO.setFechaOrden(ordenCompra.getFechaOrden());
                        ordenCompraDTO.setEstadoOrden(String.valueOf(ordenCompra.getEstadoOrden()));
                        ordenCompraDTO.setSubtotal(ordenCompra.getSubtotal());
                        ordenCompraDTO.setIGV(ordenCompra.getIGV());
                        ordenCompraDTO.setMontoTotal(ordenCompra.getMontototal());

                        return ordenCompraDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todas las ordenes de compra");
        }
    }

    @Override
    public List<OrdenCompraDTO> buscarActivo() {
        return List.of();
    }

    @Override
    public List<OrdenCompraDTO> buscarInactivo() {
        return List.of();
    }

    @Override
    public Optional<OrdenCompraDTO> buscarId(String id) {
        try {
            Optional<OrdenCompraEntity> ordenCompraEntity = ordenCompraRepository.findById(id);
            if (ordenCompraEntity.isPresent()) {
                OrdenCompraDTO ordenCompraDTO = new OrdenCompraDTO();
                ProveedorEntity proveedor = ordenCompraEntity.get().getProveedor();
                ProveedorDTO proveedorDTO = new ProveedorDTO();

                ordenCompraDTO.setId(ordenCompraEntity.get().getId());
                ordenCompraDTO.setProveedor(modelMapper.map(ordenCompraEntity.get().getProveedor(), ProveedorDTO.class));
                ordenCompraDTO.setFechaOrden(ordenCompraEntity.get().getFechaOrden());
                ordenCompraDTO.setEstadoOrden(String.valueOf(ordenCompraEntity.get().getEstadoOrden()));
                ordenCompraDTO.setSubtotal(ordenCompraEntity.get().getSubtotal());
                ordenCompraDTO.setIGV(ordenCompraEntity.get().getIGV());
                ordenCompraDTO.setMontoTotal(ordenCompraEntity.get().getMontototal());

                return Optional.of(ordenCompraDTO);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la orden de compra");
        }
    }

    @Override
    @Transactional
    public OrdenCompraDTO agregar(OrdenCompraDTO ordenCompraDTO) {
        try {
            OrdenCompraEntity ordenCompraEntity = guardarOrdenCompra(ordenCompraDTO);
            BigDecimal montoTotal = guardarDetallesOrdenCompra(ordenCompraDTO.getDetalles(), ordenCompraEntity);

            ordenCompraEntity.setMontototal(montoTotal);
            ordenCompraEntity.setIGV(montoTotal.multiply(BigDecimal.valueOf(0.18)));
            ordenCompraEntity.setSubtotal(montoTotal.subtract(ordenCompraEntity.getIGV()));

            GuiaEntradaEntity guiaEntradaEntity = crearGuiaEntrada(ordenCompraEntity);

            return modelMapper.map(ordenCompraEntity, OrdenCompraDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar la orden de compra");
        }
    }

    @Transactional
    private OrdenCompraEntity guardarOrdenCompra(OrdenCompraDTO ordenCompraDTO) {

        ProveedorEntity proveedorEntity = proveedorRepository.findById(ordenCompraDTO.getProveedor().getId()).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        OrdenCompraEntity ordenCompraEntity = modelMapper.map(ordenCompraDTO, OrdenCompraEntity.class);
        ordenCompraEntity.setId(IdGenerator.generarID("OC-", NumeroCorrelativoGenerator.generarNumeroCorrelativo()));
        ordenCompraEntity.setProveedor(proveedorEntity);
        ordenCompraEntity.setFechaOrden(LocalDate.now());
        ordenCompraEntity.setEstadoOrden(EstadoOrden.PENDIENTE);
        ordenCompraEntity.setIGV(BigDecimal.valueOf(0.18));

        return ordenCompraRepository.save(ordenCompraEntity);
    }

    @Transactional
    private BigDecimal guardarDetallesOrdenCompra(List<DetalleOrdenCompraDTO> detallesOrdenCompra, OrdenCompraEntity ordenCompra) {

        BigDecimal montoTotal = BigDecimal.valueOf(0);

        for (DetalleOrdenCompraDTO detalleOrdenCompraDTO : detallesOrdenCompra) {

            ProductoEntity producto = obtenerProducto(detalleOrdenCompraDTO.getProducto().getId());


            DetalleOrdenCompraEntity detalleOrdenCompraEntity = modelMapper.map(detalleOrdenCompraDTO, DetalleOrdenCompraEntity.class);

            BigDecimal precioUnitario = obtenerProducto(detalleOrdenCompraDTO.getProducto().getId()).getPrecio();
            BigDecimal precioTotal = precioUnitario.multiply(BigDecimal.valueOf(detalleOrdenCompraDTO.getCantidad()));

            detalleOrdenCompraEntity.setPrecioUnitario(precioUnitario);
            detalleOrdenCompraEntity.setPrecioTotal(precioTotal);
            detalleOrdenCompraEntity.setProducto(obtenerProducto(detalleOrdenCompraDTO.getProducto().getId()));

            // Actualizar stock si el estado de la orden es Completado
            if (ordenCompra.getEstadoOrden().equals(EstadoOrden.COMPLETADA)) {
                producto.setStock(producto.getStock() + detalleOrdenCompraDTO.getCantidad());
                productoRepository.save(producto);
            }

            detalleOrdenCompraEntity.setOrdenCompra(ordenCompra);
            detalleOrdenCompraRepository.save(detalleOrdenCompraEntity);

            montoTotal = montoTotal.add(precioTotal);
        }

        return montoTotal;
    }

    @Transactional
    private GuiaEntradaEntity crearGuiaEntrada(OrdenCompraEntity ordenCompra) {
        // Crear la guía de entrada
        GuiaEntradaEntity guiaEntradaEntity = GuiaEntradaEntity.builder()
                .id(IdGenerator.generarID("GUE-", NumeroCorrelativoGenerator.generarNumeroCorrelativo()))
                .fechaEntrada(LocalDate.now())
                .build();

        // Guardar la guía de entrada
        guiaEntradaRepository.save(guiaEntradaEntity);

        // Crear y guardar los detalles de la guía de entrada
        for (DetalleOrdenCompraEntity detalleOrdenCompra : ordenCompra.getDetalles()) {
            DetalleGuiaEntradaEntity detalleGuiaEntradaEntity = DetalleGuiaEntradaEntity.builder()
                    .guiaEntrada(guiaEntradaEntity)
                    .producto(detalleOrdenCompra.getProducto())
                    .build();
            detalleGuiaEntradaRepository.save(detalleGuiaEntradaEntity);
        }

        return guiaEntradaEntity;
    }

    @Transactional
    private FacturaCompraEntity crearFacturaCompra(OrdenCompraEntity ordenCompra) {
        // Crear la factura de compra
        FacturaCompraEntity facturaCompraEntity = FacturaCompraEntity.builder()
                .id(IdGenerator.generarID("FC-", NumeroCorrelativoGenerator.generarNumeroCorrelativo()))
                .fechaCompra(LocalDate.now())
                .estadoFactura(EstadoFactura.PENDIENTE)
                .build();

        // Guardar la factura de compra
        facturaCompraRepository.save(facturaCompraEntity);

        // Crear y guardar los detalles de la factura de compra
        for (DetalleOrdenCompraEntity detalleOrdenCompra : ordenCompra.getDetalles()) {
            DetalleFacturaCompraEntity detalleFacturaCompraEntity = DetalleFacturaCompraEntity.builder()
                    .facturaCompra(facturaCompraEntity)
                    .producto(detalleOrdenCompra.getProducto())
                    .precio(detalleOrdenCompra.getPrecioTotal())
                    .cantidad(detalleOrdenCompra.getCantidad())
                    .build();
            detalleFacturaCompraRepository.save(detalleFacturaCompraEntity);
        }

        return facturaCompraEntity;
    }


    private ProductoEntity obtenerProducto(String idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + idProducto));
    }


    @Override
    @Transactional
    public OrdenCompraDTO actualizar(OrdenCompraDTO ordenCompraDTO, String id) {
        // Obtener la orden de compra existente por su ID
        OrdenCompraEntity ordenCompraExistente = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró ninguna orden de compra con el ID: " + id));

        // Actualizar los campos de la orden de compra con los valores del DTO
        ordenCompraExistente.setProveedor(proveedorRepository.findById(ordenCompraDTO.getProveedor().getId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + ordenCompraDTO.getProveedor().getId())));
        ordenCompraExistente.setFechaOrden(ordenCompraDTO.getFechaOrden());
        ordenCompraExistente.setProveedor(proveedorRepository.findById(ordenCompraDTO.getProveedor().getId()).get());
        // Convertir el valor del estado del DTO a un enum EstadoOrden
        ordenCompraExistente.setEstadoOrden(EstadoOrden.valueOf(ordenCompraDTO.getEstadoOrden()));
        ordenCompraExistente.setSubtotal(ordenCompraDTO.getSubtotal());

        // Actualizar los detalles de la orden de compra
        List<DetalleOrdenCompraEntity> detallesOrdenCompra = ordenCompraDTO.getDetalles().stream()
                .map(detalleOrdenCompraDTO -> {
                    DetalleOrdenCompraEntity detalleOrdenCompraEntity = modelMapper.map(detalleOrdenCompraDTO, DetalleOrdenCompraEntity.class);
                    detalleOrdenCompraEntity.setOrdenCompra(ordenCompraExistente);
                    return detalleOrdenCompraEntity;
                })
                .toList();

        // Guarda la orden de compra actualizada en la base de datos
        OrdenCompraEntity ordenCompraActualizada = ordenCompraRepository.save(ordenCompraExistente);

        // Devolver el DTO de la orden de compra actualizada
        return modelMapper.map(ordenCompraActualizada, OrdenCompraDTO.class);
    }


    @Override
    @Transactional
    public void eliminar(String id) {
        // Obtener la orden de compra existente por su ID
        OrdenCompraEntity ordenCompraExistente = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró ninguna orden de compra con el ID: " + id));

        // Cambiar el estado de la orden de compra a "CANCELADA"
        ordenCompraExistente.setEstadoOrden(EstadoOrden.CANCELADA);

        // Guardar la orden de compra actualizada en la base de datos
        ordenCompraRepository.save(ordenCompraExistente);
    }


    @Override
    @Transactional
    public OrdenCompraDTO habilitar(String id) {
        // Obtener la orden de compra existente por su ID
        OrdenCompraEntity ordenCompraExistente = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró ninguna orden de compra con el ID: " + id));

        // Cambiar el estado de la orden de compra a "PENDIENTE"
        ordenCompraExistente.setEstadoOrden(EstadoOrden.PENDIENTE);

        // Guardar la orden de compra actualizada en la base de datos
        OrdenCompraEntity ordenCompraActualizada = ordenCompraRepository.save(ordenCompraExistente);

        // Devolver el DTO de la orden de compra actualizada
        return modelMapper.map(ordenCompraActualizada, OrdenCompraDTO.class);
    }

}
