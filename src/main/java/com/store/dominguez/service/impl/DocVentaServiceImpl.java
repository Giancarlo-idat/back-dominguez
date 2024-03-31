package com.store.dominguez.service.impl;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.service.gestion.DocVentaService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.generator.NumeroCorrelativoGenerator;
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

      /*  if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

        try {
            Optional<DocVentaEntity> docVentaEntity = docVentaRepository.findById(id);
            if (docVentaEntity.isPresent()) {
                return docVentaEntity.map(docVenta -> modelMapper.map(docVenta, DocVentaDTO.class));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la categoria" + e.getMessage());
        }*/
        return Optional.empty();
    }

    @Override
    public DocVentaDTO agregar(DocVentaDTO docVentaDTO) {
        docVentaValidator.validarDocVenta(docVentaDTO);

        try {
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
            docVentaEntity.setIdCliente(clienteEntity);
            DocVentaEntity docVentaSaved = docVentaRepository.save(docVentaEntity);

            // Crear los detalles de venta
            for (DocDetalleVentaDTO detalle : docVentaDTO.getDetallesVenta()) {
                DocDetalleVentaEntity detalleEntity = modelMapper.map(detalle, DocDetalleVentaEntity.class);
                detalleEntity.setVenta(docVentaSaved); // Asignar la venta recién creada al detalle de venta
                docDetalleVentaRepository.save(detalleEntity);
            }

            // Mapear la venta guardada a DTO y devolverla
            return modelMapper.map(docVentaSaved, DocVentaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    private ProductoEntity obtenerProducto(String idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + idProducto));
    }

    @Override
    public DocVentaDTO actualizar(DocVentaDTO docVentaDTO, String id) {

        /*if (Validations.isBlank(id)) throw new NullPointerException("El id no puede ser nulo o vacio");

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
        }*/
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
