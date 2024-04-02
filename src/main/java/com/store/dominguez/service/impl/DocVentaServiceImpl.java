package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.security.jwt.JwtUtils;
import com.store.dominguez.service.gestion.DocVentaService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.generator.NumeroCorrelativoGenerator;
import com.store.dominguez.util.generator.NumeroGuiaGenerator;
import com.store.dominguez.util.generator.NumeroSeguimientoGenerator;
import com.store.dominguez.util.validations.DocVentaValidator;
import com.store.dominguez.util.validations.Validations;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final ModelMapper modelMapper;
    private final DocVentaValidator docVentaValidator;

    @Autowired
    public DocVentaServiceImpl(DocVentaRepository docVentaRepository, ClienteRepository clienteRepository, ModelMapper modelMapper, DocVentaValidator docVentaValidator, NumeroSeguimientoGenerator numeroSeguimientoGenerator, DocDetalleVentaRepository docDetalleVentaRepository, ProductoRepository productoRepository, GuiaSalidaRepository guiaSalidaRepository, DetalleGuiaSalidaRepository detalleGuiaSalidaRepository, TipoTransaccionRepository tipoTransaccionRepository) {
        this.docVentaRepository = docVentaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.docVentaValidator = docVentaValidator;
        this.docDetalleVentaRepository = docDetalleVentaRepository;
        this.productoRepository = productoRepository;
        this.guiaSalidaRepository = guiaSalidaRepository;
        this.detalleGuiaSalidaRepository = detalleGuiaSalidaRepository;
        this.tipoTransaccionRepository = tipoTransaccionRepository;
    }

    @Override
    public List<DocVentaDTO> buscarTodos() {
        try {
            List<DocVentaEntity> list = docVentaRepository.findAll();
            System.out.println(list);
            return list.stream()
                    .map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class))
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
        return Optional.empty();
    }

    public Optional<DocVentaDTO> buscarIdDocVenta(UUID id) {
        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findByIdVenta(id);
            return docVentaEntity.map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }
    }

    @Override
    public boolean existsByIdAndClienteEmail(UUID idDocumento, String emailCliente) {
        try {
            return docVentaRepository.existsByIdVentaAndClienteEmail(idDocumento, emailCliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar: " + e.getMessage());
        }
    }


    @Override
    public DocVentaDTO agregar(DocVentaDTO docVentaDTO) {
        docVentaValidator.validarDocVenta(docVentaDTO);

        try {
            // Guardar la venta
            DocVentaEntity docVentaSaved = guardarVenta(docVentaDTO);

            // Guardar los detalles de venta
            guardarDetallesDeVenta(docVentaDTO.getDetallesVenta(), docVentaSaved);

            // Crear y guardar la guía de salida
            GuiaSalidaEntity guiaSalidaEntity = crearGuiaSalida(docVentaSaved);

            // Mapear la venta guardada a DTO y devolverla
            return modelMapper.map(docVentaSaved, DocVentaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private DocVentaEntity guardarVenta(DocVentaDTO docVentaDTO) {
        // Tipo de transacción
        TipoTransaccionEntity tipoTransaccionEntity = tipoTransaccionRepository.findById(docVentaDTO.getTipoTransaccion().getId())
                .orElseThrow(() -> new NoSuchElementException("Tipo de transacción no encontrado con el ID: " + docVentaDTO.getTipoTransaccion().getId()));

        // Cliente
        ClienteEntity clienteEntity = clienteRepository.findById(docVentaDTO.getCliente().getId())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con el ID: " + docVentaDTO.getCliente().getId()));

        // Crear la venta
        DocVentaEntity docVentaEntity = modelMapper.map(docVentaDTO, DocVentaEntity.class);
        docVentaEntity.setNumComprobante(NumeroCorrelativoGenerator.generarNumeroCorrelativo());
        docVentaEntity.setFechaEnvio(docVentaDTO.getFechaEntrega());
        docVentaEntity.setEstadoEnvio(EstadoEnvio.PENDIENTE);
        docVentaEntity.setIdTipoTransaccion(tipoTransaccionEntity);
        docVentaEntity.setCliente(clienteEntity);
        return docVentaRepository.save(docVentaEntity);
    }

    private void guardarDetallesDeVenta(List<DocDetalleVentaDTO> detallesVenta, DocVentaEntity docVentaEntity) {
        // Crear los detalles de venta
        for (DocDetalleVentaDTO detalle : detallesVenta) {
            DocDetalleVentaEntity detalleEntity = modelMapper.map(detalle, DocDetalleVentaEntity.class);
            detalleEntity.setVenta(docVentaEntity); // Asignar la venta recién creada al detalle de venta
            docDetalleVentaRepository.save(detalleEntity);
        }
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
