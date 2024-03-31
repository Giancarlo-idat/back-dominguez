package com.store.dominguez.controller;


import com.store.dominguez.dto.ClienteDTO;
import com.store.dominguez.service.gestion.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(clienteService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<ClienteDTO>> buscarActivos() {
        try {
            return ResponseEntity.ok(clienteService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<ClienteDTO>> buscarInactivos() {
        try {
            return ResponseEntity.ok(clienteService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Optional<?>> buscarId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(clienteService.buscarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.ofNullable(e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String cliente) {

        try {
            List<ClienteDTO> clientes = clienteService.buscarDatosCliente(cliente);
            return ResponseEntity.ok(clientes);
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }

    }

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> agregar(@RequestBody ClienteDTO ClienteEntity) {
        try {
            return ResponseEntity.status(201).body(clienteService.agregar(ClienteEntity));
        } catch (DataIntegrityViolationException e) {
            // Maneja la excepción de clave duplicada
            return ResponseEntity.status(400).body("Error al guardar el cliente: ya existe un cliente con el mismo correo electrónico.");
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> actualizar(@RequestBody ClienteDTO ClienteEntity, @PathVariable String id) {
        try {
            return ResponseEntity.ok(clienteService.actualizar(ClienteEntity, id));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            return ResponseEntity.ok(clienteService.habilitar(id));
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            clienteService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
