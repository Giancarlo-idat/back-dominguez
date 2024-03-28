package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.service.gestion.DocVentaService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.generator.NumeroCorrelativoGenerator;
import com.store.dominguez.util.generator.NumeroSeguimientoGenerator;
import com.store.dominguez.util.validations.DocVentaValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocVentaServiceImpl implements DocVentaService {

    private final DocVentaRepository docVentaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final GuiaSalidaRepository guiaSalidaRepository;
    private final DetalleGuiaSalidaRepository detalleGuiaSalidaRepository;
    private final ModelMapper modelMapper;
    private final DocVentaValidator docVentaValidator;

    @Autowired
    public DocVentaServiceImpl(DocVentaRepository docVentaRepository, ClienteRepository clienteRepository, ModelMapper modelMapper, DocVentaValidator docVentaValidator, NumeroSeguimientoGenerator numeroSeguimientoGenerator, ProductoRepository productoRepository, GuiaSalidaRepository guiaSalidaRepository, DetalleGuiaSalidaRepository detalleGuiaSalidaRepository) {
        this.docVentaRepository = docVentaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.docVentaValidator = docVentaValidator;
        this.productoRepository = productoRepository;
        this.guiaSalidaRepository = guiaSalidaRepository;
        this.detalleGuiaSalidaRepository = detalleGuiaSalidaRepository;
    }

    @Override
    public List<DocVentaDTO> buscarTodos() {
        try {
            List<DocVentaEntity> list = docVentaRepository.findAll();
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DocVentaDTO> buscarActivo() {
        try {
            List<DocVentaEntity> docVentaEntityList = docVentaRepository.buscarDocVentaActivo();
            return docVentaEntityList.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DocVentaDTO> buscarInactivo() {
        try {
            List<DocVentaEntity> docVentaEntityList = docVentaRepository.buscarDocVentaInactivo();
            return docVentaEntityList.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public Optional<DocVentaDTO> buscarId(String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isPresent()) {
                return docVentaEntity.map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }

    }

    @Override
    public DocVentaDTO agregar(DocVentaDTO docVentaDTO) {
        docVentaValidator.validarDocVenta(docVentaDTO);
        try {
            Optional<ClienteEntity> idClienteOptional = clienteRepository.findById(docVentaDTO.getCliente().getId());
            if (idClienteOptional.isEmpty()) {
                throw new NoSuchElementException("Cliente no encontrado con el ID: " + docVentaDTO.getCliente().getId());
            }
            ClienteEntity idCliente = idClienteOptional.get();

            DocVentaEntity docVenta = new DocVentaEntity();
            // ID del documento de venta
            String id = IdGenerator.generarID("DV", docVentaDTO.getCliente().getId());
            // Numero de seguimiento
            String numeroSeguimiento = NumeroSeguimientoGenerator.generarNumeroSeguimiento();
            // Numero de correlativo
            String correlativo = NumeroCorrelativoGenerator.generarNumeroCorrelativo();
            // Setear los valores
            docVenta.setId(id);
            docVenta.setNumeroSeguimiento(numeroSeguimiento);
            docVenta.setIdCliente(idCliente);

            Set<DocDetalleVentaEntity> detallesVenta = new HashSet<>();

            // Crear la guía de salida y su detalle
            GuiaSalidaEntity guiaSalida = new GuiaSalidaEntity();
            guiaSalida.setFechaSalida(LocalDate.now());
            guiaSalida.setCliente(idCliente);
            guiaSalida = guiaSalidaRepository.save(guiaSalida);

            for (DocDetalleVentaDTO detalleVentaDTO : docVentaDTO.getDetalleVenta()) {
                DocDetalleVentaEntity detalleVentaEntity = new DocDetalleVentaEntity();
                Optional<ProductoEntity> idProductOptional = productoRepository.findById(detalleVentaDTO.getProducto().getId());
                if (idProductOptional.isEmpty()) {
                    throw new NoSuchElementException("Producto no encontrado con el ID: " + detalleVentaDTO.getProducto().getId());
                }
                ProductoEntity productoEntity = idProductOptional.get();

                // Verificar si el producto tiene suficiente stock
                if (productoEntity.getStock() < detalleVentaDTO.getCantidad())
                    throw new IllegalArgumentException("El producto " + productoEntity.getModelo() + " no tiene suficiente stock");

                // Reduce el stock del producto
                productoEntity.setStock(productoEntity.getStock() - detalleVentaDTO.getCantidad());

                detalleVentaEntity.setId(IdGenerator.generarID("DTV", NumeroCorrelativoGenerator.generarNumeroCorrelativo()));
                detalleVentaEntity.setCantidad(detalleVentaDTO.getCantidad());
                detalleVentaEntity.setProductos(productoEntity);
                detalleVentaEntity.setPrecio_unitario(detalleVentaDTO.getPrecio_unitario());
                detalleVentaEntity.setPrecio_total(detalleVentaDTO.getPrecio_total());
                detalleVentaEntity.setDocVenta(docVenta);
                detallesVenta.add(detalleVentaEntity);

                // Crea guía de salida
                DetalleGuiaSalidaEntity detalleGuiaSalida = new DetalleGuiaSalidaEntity();
                detalleGuiaSalida.setGuiaSalida(guiaSalida);
                detalleGuiaSalida.setProducto(productoEntity);
                detalleGuiaSalida.setCantidad(detalleVentaDTO.getCantidad());
                detalleGuiaSalidaRepository.save(detalleGuiaSalida);
            }

            docVenta.setDetalleVenta(detallesVenta);
            docVenta.calcularPrecioTotal();

            DocVentaEntity docVentaSave = docVentaRepository.save(docVenta);

            return modelMapper.map(docVentaSave, DocVentaDTO.class);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            throw new RuntimeException("Error al agregar el documento de venta: " + e.getMessage());
        }
    }

    @Override
    public DocVentaDTO actualizar(DocVentaDTO docVentaDTO, String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

        docVentaValidator.validarDocVenta(docVentaDTO);
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isPresent()) {
                DocVentaEntity docVenta = docVentaEntity.get();
                docVenta = modelMapper.map(docVentaDTO, DocVentaEntity.class);
                docVenta = docVentaRepository.save(docVenta);
                return modelMapper.map(docVenta, DocVentaDTO.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {

        if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isEmpty()) throw new Error("El id no existe");


        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public DocVentaDTO habilitar(String id) {
        return null;
    }

    @Override
    public List<DocVentaDTO> buscarDocVentaPorNumeroSeguimiento(String datos) {

        if (Validations.isBlank(datos)) throw new NullPointerException("El dato no puede ser nulo o vacio");

        try {
            List<DocVentaEntity> list = docVentaRepository.buscarDocVentaPorNumeroSeguimiento(datos);
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public List<DocVentaDTO> buscarDocVentaPorEstadoEnvio(EstadoEnvio estadoEnvio) {

        if (Validations.isBlank(estadoEnvio.name()))
            throw new NullPointerException("El dato no puede ser nulo o vacio");

        try {
            List<DocVentaEntity> list = docVentaRepository.buscarDocVentaPorEstadoEnvio(estadoEnvio);
            return list.stream().map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }
}
