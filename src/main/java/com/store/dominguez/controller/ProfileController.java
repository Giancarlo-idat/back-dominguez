package com.store.dominguez.controller;

import com.store.dominguez.dto.ClienteDTO;
import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.dto.RolDTO;
import com.store.dominguez.dto.TipoDocumentoIdentidadDTO;
import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.model.EmpleadoEntity;
import com.store.dominguez.model.RolEntity;
import com.store.dominguez.model.TipoDocumentoIdentidadEntity;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.repository.gestion.EmpleadoRepository;
import com.store.dominguez.repository.gestion.RolRepository;
import com.store.dominguez.repository.gestion.TipoDocumentoIdentidadRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RolRepository rolRepository;
    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;

    public ProfileController(ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository, RolRepository rolRepository, TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository) {
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.rolRepository = rolRepository;
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;

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

        TipoDocumentoIdentidadEntity tipoDocumentoEntity = cliente.getTipoDocumento();
        if (tipoDocumentoEntity != null) {
            String tipoDocumentoId = tipoDocumentoEntity.getId();
            TipoDocumentoIdentidadEntity documentoEntity = tipoDocumentoIdentidadRepository.findById(tipoDocumentoId)
                    .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
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
            tipoDocumentoIdentidadDTO.setNombre(documentoEntity.getNombre());
            empleadoDTO.setTipoDocumento(tipoDocumentoIdentidadDTO);
        } else {
            throw new RuntimeException("Tipo de documento no especificado para el empleado");
        }


        empleadoDTO.setSexo(empleado.getSexo());

        return empleadoDTO;
    }


}