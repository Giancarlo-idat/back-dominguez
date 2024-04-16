package com.store.dominguez.service.impl;

import com.store.dominguez.dto.*;
import com.store.dominguez.model.*;
import com.store.dominguez.model.enums.EstadoEnvio;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.service.gestion.DocVentaService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.generator.NumeroGuiaGenerator;
import com.store.dominguez.util.generator.NumeroSeguimientoGenerator;
import com.store.dominguez.util.validations.DocVentaValidator;
import com.store.dominguez.util.validations.Validations;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocVentaServiceImpl implements DocVentaService {

    private final DocVentaRepository docVentaRepository;
    private final DocDetalleVentaRepository docDetalleVentaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final GuiaSalidaRepository guiaSalidaRepository;
    private final DetalleGuiaSalidaRepository detalleGuiaSalidaRepository;
    private final TipoTransaccionRepository tipoTransaccionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ModelMapper modelMapper;
    private final DocVentaValidator docVentaValidator;

    @Autowired
    public DocVentaServiceImpl(DocVentaRepository docVentaRepository, ClienteRepository clienteRepository, ModelMapper modelMapper, DocVentaValidator docVentaValidator, NumeroSeguimientoGenerator numeroSeguimientoGenerator, DocDetalleVentaRepository docDetalleVentaRepository, ProductoRepository productoRepository, GuiaSalidaRepository guiaSalidaRepository, DetalleGuiaSalidaRepository detalleGuiaSalidaRepository, TipoTransaccionRepository tipoTransaccionRepository, EmpleadoRepository empleadoRepository) {
        this.docVentaRepository = docVentaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.docVentaValidator = docVentaValidator;
        this.docDetalleVentaRepository = docDetalleVentaRepository;
        this.productoRepository = productoRepository;
        this.guiaSalidaRepository = guiaSalidaRepository;
        this.detalleGuiaSalidaRepository = detalleGuiaSalidaRepository;
        this.tipoTransaccionRepository = tipoTransaccionRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<DocVentaDTO> buscarTodos() {
        try {
            List<DocVentaEntity> list = docVentaRepository.findAll();
            return list.stream()
                    .map(docVenta -> {
                        DocVentaDTO docVentaDTO = new DocVentaDTO();
                        ClienteEntity clienteEntity = docVenta.getCliente();
                        ClienteDTO clienteDTO = new ClienteDTO();

                        docVentaDTO.setId(((docVenta.getId())));
                        docVentaDTO.setFechaEntrega(docVenta.getFechaEntrega());
                        docVentaDTO.setCliente(modelMapper.map(docVenta.getCliente(), ClienteDTO.class));
                        docVentaDTO.setEstadoEnvio(docVenta.getEstadoEnvio());
                        docVentaDTO.setNumComprobante(docVenta.getNumComprobante());
                        docVentaDTO.setPrecioTotal(docVenta.getPrecioTotal());
                        docVentaDTO.setOpGravadas(docVenta.getOpGravadas());
                        docVentaDTO.setIgv(docVenta.getIgv());
                        docVentaDTO.setEstado(docVenta.isEstado());
                        docVentaDTO.setFechaEnvio(docVentaDTO.getFechaEntrega());
                        docVentaDTO.setTipoTransaccion(modelMapper.map(docVenta.getTipoTransaccion(), TipoTransaccionDTO.class));
                        docVentaDTO.setPrecioTotal(docVenta.getPrecioTotal());
                        docVentaDTO.setDetallesVenta(docVenta.getDetallesVenta().stream()
                                .map(docDetalleVenta -> {
                                    DocDetalleVentaDTO docDetalleVentaDTO = new DocDetalleVentaDTO();
                                    docDetalleVentaDTO.setId((docDetalleVenta.getId()));
                                    docDetalleVentaDTO.setCantidad(docDetalleVenta.getCantidad());
                                    docDetalleVentaDTO.setPrecioUnitario(docDetalleVenta.getPrecioUnitario());
                                    docDetalleVentaDTO.setPrecioTotal(docDetalleVenta.getPrecioTotal());
                                    docDetalleVentaDTO.setProductos(modelMapper.map(docDetalleVenta.getProductos(), ProductoDTO.class));
                                    return docDetalleVentaDTO;
                                })
                                .collect(Collectors.toList()));
                        return docVentaDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar: " + e.getMessage());
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
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            return docVentaEntity.map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el documento" + e.getMessage());
        }
    }


    @Override
    public boolean existsByIdAndClienteEmail(String idDocumento, String emailCliente) {
        try {
            return docVentaRepository.existsByIdAndClienteEmail(idDocumento, emailCliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar: " + e.getMessage());
        }
    }


    @Override
    @Transactional
    public DocVentaDTO agregar(DocVentaDTO docVentaDTO) {
        docVentaValidator.validarDocVenta(docVentaDTO);
        try {
            // Guardar la venta
            DocVentaEntity docVentaSaved = guardarVenta(docVentaDTO);

            // Guardar los detalles de venta y obtener el precio total de la venta
            BigDecimal precioTotalVenta = guardarDetallesDeVenta(docVentaDTO.getDetallesVenta(), docVentaSaved);

            // Actualizar el precio total y el IGV de la venta
            docVentaSaved.setPrecioTotal(precioTotalVenta);
            docVentaSaved.setIgv(precioTotalVenta.multiply(BigDecimal.valueOf(0.18)));


            // Crear y guardar la guía de salida
            GuiaSalidaEntity guiaSalidaEntity = crearGuiaSalida(docVentaSaved);

            // Mapear la venta guardada a DTO y devolverla
            return modelMapper.map(docVentaSaved, DocVentaDTO.class);
        } catch (NoSuchElementException e) {
            // Manejar la excepción de elemento no encontrado
            throw new RuntimeException("Error al agregar la venta: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otras excepciones
            throw new RuntimeException("Error al agregar la venta.", e);
        }
    }

    private DocVentaEntity guardarVenta(DocVentaDTO docVentaDTO) {
        // Tipo de transacción
        TipoTransaccionEntity tipoTransaccionEntity = tipoTransaccionRepository.findById("TTR-VENTA-TOC201")
                .orElseThrow(() -> new NoSuchElementException("Tipo de transacción por defecto no encontrado"));

        // Cliente
        ClienteEntity clienteEntity = clienteRepository.findById(docVentaDTO.getCliente().getId())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con el ID: " + docVentaDTO.getCliente().getId()));

        // Crear la venta
        DocVentaEntity docVentaEntity = modelMapper.map(docVentaDTO, DocVentaEntity.class);
        docVentaEntity.setId(IdGenerator.generarID("BXV-", String.valueOf(UUID.randomUUID())));
        docVentaEntity.setNumComprobante(NumeroSeguimientoGenerator.generarNumeroSeguimiento());
        docVentaEntity.setFechaEnvio(docVentaDTO.getFechaEntrega());
        docVentaEntity.setEstadoEnvio(EstadoEnvio.PENDIENTE);
        docVentaEntity.setTipoTransaccion(tipoTransaccionEntity);
        docVentaEntity.setCliente(clienteEntity);
        return docVentaRepository.save(docVentaEntity);
    }

    private BigDecimal guardarDetallesDeVenta(List<DocDetalleVentaDTO> detallesVenta, DocVentaEntity docVentaEntity) {
        BigDecimal precioTotalVenta = BigDecimal.ZERO; // Inicializamos el precio total de la venta

        // Itera sobre los detalles de venta
        for (DocDetalleVentaDTO detalle : detallesVenta) {


            ProductoEntity producto = obtenerProducto(detalle.getProductos().getId());
            if(producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el producto con ID: " + producto.getId());
            }

            DocDetalleVentaEntity detalleEntity = modelMapper.map(detalle, DocDetalleVentaEntity.class);

            // Buscar el precio unitario del producto y calcular el precio total del detalle
            BigDecimal precioUnitario = obtenerProducto(detalle.getProductos().getId()).getPrecio();
            BigDecimal precioTotalDetalle = precioUnitario.multiply(BigDecimal.valueOf(detalle.getCantidad()));

            detalleEntity.setPrecioUnitario(precioUnitario);
            detalleEntity.setPrecioTotal(precioTotalDetalle);
            detalleEntity.setProductos(obtenerProducto(detalle.getProductos().getId()));

            // Actualizar el stock del producto
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            if (detallesVenta.indexOf(detalle) == 0) {
                detalleEntity.setVenta(docVentaEntity); // Asignar la venta recién creada al detalle de venta
                docDetalleVentaRepository.save(detalleEntity);
            } else {
                detalleEntity.setVenta(docVentaEntity); // Asignar la venta recién creada al detalle de venta
                docDetalleVentaRepository.save(detalleEntity);
            }

            // Sumar el precio total del detalle al precio total de la venta
            precioTotalVenta = precioTotalVenta.add(precioTotalDetalle);
        }

        return precioTotalVenta;
    }

    private GuiaSalidaEntity crearGuiaSalida(DocVentaEntity docVentaEntity) {
        new GuiaSalidaEntity();
        GuiaSalidaEntity guiaSalidaEntity = GuiaSalidaEntity.builder()
                .fechaSalida(LocalDate.now())
                .numeroGuiaSalida(NumeroGuiaGenerator.generarNumeroGuiaSalida())
                .numeroSalida(NumeroGuiaGenerator.generarNumeroSalida())
                .cliente(docVentaEntity.getCliente())
                .build();
        guiaSalidaRepository.save(guiaSalidaEntity);

        for (DocDetalleVentaEntity detalleVenta : docVentaEntity.getDetallesVenta()) {
            DetalleGuiaSalidaEntity detalleGuiaSalidaEntity = DetalleGuiaSalidaEntity.builder()
                    .numeroDetalle(NumeroGuiaGenerator.generarNumeroSalida())
                    .cantidad(detalleVenta.getCantidad())
                    .productos(detalleVenta.getProductos())
                    .guiaSalida(guiaSalidaEntity)
                    .build();
            detalleGuiaSalidaRepository.save(detalleGuiaSalidaEntity);
        }
        return guiaSalidaEntity;
    }

    private ProductoEntity obtenerProducto(String idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + idProducto));
    }

    @Override
    public DocVentaDTO actualizar(DocVentaDTO docVentaDTO, String id) {

        return null;
    }

    @Override
    public void eliminar(String id) {

       /* if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isEmpty()) throw new Error("El id no existe");


        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }*/

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