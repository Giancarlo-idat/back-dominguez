package com.store.dominguez.controller;

import com.store.dominguez.dto.*;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import com.store.dominguez.service.gestion.DocVentaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RolRepository rolRepository;
    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;
    private final DocVentaRepository docVentaRepository;
    private final DocDetalleVentaRepository docDetalleVentaRepository;
    private final ModelMapper modelMapper;

    public ProfileController(ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository, RolRepository rolRepository, TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository, DocVentaRepository docVentaRepository, DocDetalleVentaRepository docDetalleVentaRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.rolRepository = rolRepository;
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;
        this.docVentaRepository = docVentaRepository;
        this.docDetalleVentaRepository = docDetalleVentaRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/myorders/client")
    @PreAuthorize("hasRole('Cliente')")
    public ResponseEntity<?> getMyOrdersClient(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            String email = userDetails.getUsername();
            ClienteEntity cliente = clienteRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con email: " + email));

            List<DocVentaEntity> docVentas = docVentaRepository.findByClienteId(cliente.getId());

            if (docVentas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("No se encontraron ventas para el cliente con ID: " + cliente.getId()));
            }

            // Ordenar la lista por fecha de creación más reciente
            docVentas.sort(Comparator.comparing(DocVentaEntity::getFechaCreacion).reversed());

            List<DocVentaDTO> orders = new ArrayList<>();

            for (DocVentaEntity docVenta : docVentas) {
                DocVentaDTO orderDTO = new DocVentaDTO();
                // Mapear campos relevantes de DocVentaEntity a DocVentaDTO
                orderDTO.setId(docVenta.getId());
                orderDTO.setCliente(modelMapper.map(docVenta.getCliente(), ClienteDTO.class));
                orderDTO.setDetallesVenta(docVenta.getDetallesVenta().stream()
                        .map(detalleVenta -> modelMapper.map(detalleVenta, DocDetalleVentaDTO.class))
                        .collect(Collectors.toList()));
                orderDTO.setTipoTransaccion(modelMapper.map(docVenta.getTipoTransaccion(), TipoTransaccionDTO.class));
                orderDTO.setEstadoEnvio(docVenta.getEstadoEnvio());
                orderDTO.setNumComprobante(docVenta.getNumComprobante());
                orderDTO.setOpGravadas(docVenta.getOpGravadas());
                orderDTO.setIgv(docVenta.getIgv());
                orderDTO.setPrecioTotal(docVenta.getPrecioTotal());
                orderDTO.setFechaEnvio(docVenta.getFechaEntrega());
                orderDTO.setFechaEntrega(docVenta.getFechaEntrega());
                orderDTO.setFechaCreacion(docVenta.getFechaCreacion());
                orderDTO.setFechaActualizacion(docVenta.getFechaActualizacion());
                // Otros campos...
                orders.add(orderDTO);
            }

            return ResponseEntity.status(HttpStatus.OK).body(orders);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList(ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/myorders/details")
    @PreAuthorize("hasRole('Cliente')")
    public ResponseEntity<?> getOrderDetailsClient(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        ClienteEntity cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con email: " + email));
        List<DocDetalleVentaEntity> detalleVentas = docDetalleVentaRepository.findAll();

        if (detalleVentas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("No se encontraron detalles de venta"));
        }

        detalleVentas.sort(Comparator.comparing(DocDetalleVentaEntity::getFechaCreacion).reversed());

        List<DocDetalleVentaDTO> detalleVentasDTO = detalleVentas.stream()
                .map(detalleVenta -> modelMapper.map(detalleVenta, DocDetalleVentaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(detalleVentasDTO);
    }

    @GetMapping("/order/{orderId}/details")
    @PreAuthorize("hasRole('Cliente')")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") String orderId) {
        try {


            // Buscar la venta por su ID
            Optional<DocVentaEntity> optionalDocVenta = docVentaRepository.findById(orderId);
            if (optionalDocVenta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la venta con el ID: " + orderId);
            }

            // Recuperar los detalles de venta asociados a la venta
            List<DocDetalleVentaEntity> detallesVenta = optionalDocVenta.get().getDetallesVenta();
            if (detallesVenta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron detalles de venta para la venta con el ID: " + orderId);
            }

            // Mapear los detalles de venta a DTOs
            List<DocDetalleVentaDTO> detallesVentaDTO = detallesVenta.stream()
                    .map(detalleVenta -> modelMapper.map(detalleVenta, DocDetalleVentaDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(detallesVentaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al recuperar los detalles de venta: " + e.getMessage());
        }
    }


    @GetMapping("/info/client")
    public ClienteDTO getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        ClienteEntity cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con email: " + email));
        System.out.println("Cliente: " + cliente);
        ClienteDTO clienteDTO = new ClienteDTO();
        ClienteEntity clientFor = new ClienteEntity();
        TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO = new TipoDocumentoIdentidadDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setNombres(cliente.getNombres());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setNumeroDocumento(cliente.getNumeroDocumento());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setPassword(cliente.getPassword());
        if (cliente.getRol() != null) {
            String rolId = cliente.getRol().getId();
            RolEntity rolEntity = rolRepository.findById(rolId)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            RolDTO rolDTO = new RolDTO();
            rolDTO.setId(rolEntity.getId());
            rolDTO.setNombre(rolEntity.getNombre());
            rolDTO.setDescripcion(rolEntity.getDescripcion());
            rolDTO.setEstado(rolEntity.isEstado());
            clienteDTO.setRol(rolDTO);
        } else {
            throw new RuntimeException("El rol del cliente es null");
        }

        TipoDocumentoIdentidadEntity tipoDocumentoEntity = cliente.getTipoDocumento();
        if (tipoDocumentoEntity != null) {
            String tipoDocumentoId = tipoDocumentoEntity.getId();
            TipoDocumentoIdentidadEntity documentoEntity = tipoDocumentoIdentidadRepository.findById(tipoDocumentoId)
                    .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
            tipoDocumentoIdentidadDTO.setId(documentoEntity.getId());
            tipoDocumentoIdentidadDTO.setNombre(documentoEntity.getNombre());
            clienteDTO.setTipoDocumento(tipoDocumentoIdentidadDTO);
        } else {
            throw new RuntimeException("Tipo de documento no especificado para el empleado");
        }

        clienteDTO.setSexo(cliente.getSexo());

        return clienteDTO;
    }

    @GetMapping("/info/employee")
    public EmpleadoDTO getEmployeeProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        EmpleadoEntity empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado con email: " + email));

        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        EmpleadoEntity empleadoFor = new EmpleadoEntity();
        TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO = new TipoDocumentoIdentidadDTO();

        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setEmail(empleado.getEmail());
        empleadoDTO.setNombres(empleado.getNombres());
        empleadoDTO.setApellidos(empleado.getApellidos());
        empleadoDTO.setPassword(empleado.getPassword());
        empleadoDTO.setTelefono(empleado.getTelefono());
        empleadoDTO.setNumeroDocumento(empleado.getNumeroDocumento());
        empleadoDTO.setDireccion(empleado.getDireccion());
        if (empleado.getRol() != null) {
            String rolId = empleado.getRol().getId();
            RolEntity rolEntity = rolRepository.findById(rolId)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            RolDTO rolDTO = new RolDTO();
            rolDTO.setId(rolEntity.getId());
            rolDTO.setNombre(rolEntity.getNombre());
            rolDTO.setDescripcion(rolEntity.getDescripcion());
            rolDTO.setEstado(rolEntity.isEstado());
            empleadoDTO.setRol(rolDTO);
        } else {
            throw new RuntimeException("El rol del empleado es null");
        }

        TipoDocumentoIdentidadEntity tipoDocumentoEntity = empleado.getTipoDocumento();
        if (tipoDocumentoEntity != null) {
            String tipoDocumentoId = tipoDocumentoEntity.getId();
            TipoDocumentoIdentidadEntity documentoEntity = tipoDocumentoIdentidadRepository.findById(tipoDocumentoId)
                    .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
            tipoDocumentoIdentidadDTO.setId(documentoEntity.getId());
            tipoDocumentoIdentidadDTO.setNombre(documentoEntity.getNombre());
            empleadoDTO.setTipoDocumento(tipoDocumentoIdentidadDTO);
        } else {
            throw new RuntimeException("Tipo de documento no especificado para el empleado");
        }


        empleadoDTO.setSexo(empleado.getSexo());

        return empleadoDTO;
    }


}