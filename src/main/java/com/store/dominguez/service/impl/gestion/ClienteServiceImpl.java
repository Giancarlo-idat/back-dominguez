package com.store.dominguez.service.impl.gestion;


import com.store.dominguez.dto.ClienteDTO;
import com.store.dominguez.model.ClienteEntity;
import com.store.dominguez.repository.gestion.ClienteRepository;
import com.store.dominguez.service.gestion.ClienteService;
import com.store.dominguez.util.validations.ClienteValidator;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    private final ClienteValidator clienteValidator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper, ClienteValidator clienteValidator, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.clienteValidator = clienteValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ClienteDTO> buscarTodos() {
        try {
            List<ClienteEntity> list = clienteRepository.findAll();
            return list.stream().map(cat -> modelMapper.map(cat, ClienteDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el cliente" + e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> buscarActivo() {
        try {
            List<ClienteEntity> list = clienteRepository.buscarClienteActivo();
            return list.stream().map(cat -> modelMapper.map(cat, ClienteDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el cliente" + e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> buscarInactivo() {
        try {
            List<ClienteEntity> list = clienteRepository.buscarClienteInactivo();
            return list.stream().map(cat -> modelMapper.map(cat, ClienteDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el cliente" + e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> buscarDatosCliente(String datos) {

        if (Validations.isBlank(datos)) throw new IllegalArgumentException("Debes ingresar un dato");

        try {
            List<ClienteEntity> listaClientes = clienteRepository.buscarDatosCliente(datos);
            return listaClientes.stream()
                    .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los datos del cliente: " + e.getMessage());
        }
    }

    @Override
    public Optional<ClienteDTO> buscarId(String id) {
        ClienteValidator.validarId(id);
        try {

            Optional<ClienteEntity> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                return Optional.of(modelMapper.map(cliente.get(), ClienteDTO.class));
            } else {
                throw new RuntimeException("El cliente no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el cliente" + e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> agregar(List<ClienteDTO> listaClientesDTO) {

        List<ClienteDTO> clientesAgregados = new ArrayList<>();

        for (ClienteDTO clienteDTO : listaClientesDTO) {
            clienteValidator.validarCliente(clienteDTO);
            try {
                String id = IdGenerator.generarID("CLI", (clienteDTO.getNombres().trim() + clienteDTO.getApellidos()));
                clienteDTO.setId(id);
                clienteDTO.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
                ClienteEntity cliente = modelMapper.map(clienteDTO, ClienteEntity.class);
                cliente = clienteRepository.save(cliente);
                clientesAgregados.add(modelMapper.map(cliente, ClienteDTO.class));
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar el cliente" + e.getMessage());
            }
        }

        return clientesAgregados;
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO clienteDTO, String id) {

        ClienteValidator.validarId(id);

        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                clienteValidator.validarCliente(clienteDTO);
                ClienteEntity clienteEntity = cliente.get();
                modelMapper.map(clienteDTO, clienteEntity);
                clienteEntity.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
                clienteEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(clienteRepository.save(clienteEntity), ClienteDTO.class);
            } else {
                throw new RuntimeException("El cliente no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {

        ClienteValidator.validarId(id);

        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                ClienteEntity clienteEntity = cliente.get();
                clienteEntity.setEstado(false);
                clienteEntity.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(clienteRepository.save(clienteEntity), ClienteDTO.class);
            } else {
                throw new RuntimeException("El cliente no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente" + e.getMessage());
        }
    }

    @Override
    public ClienteDTO habilitar(String id) {

        ClienteValidator.validarId(id);

        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                ClienteEntity clienteEntity = cliente.get();
                clienteEntity.setEstado(true);
                clienteEntity.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(clienteRepository.save(clienteEntity), ClienteDTO.class);
            } else {
                throw new RuntimeException("El cliente no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al habilitar el cliente" + e.getMessage());
        }
    }

}
