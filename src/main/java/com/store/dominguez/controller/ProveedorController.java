package com.store.dominguez.controller;


import com.store.dominguez.dto.ProveedorDTO;
import com.store.dominguez.service.gestion.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ProveedorDTO>> buscarTodos() {
        try {
            return ResponseEntity.ok(proveedorService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ProveedorDTO>> buscarActivos() {
        try {
            return ResponseEntity.ok(proveedorService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ProveedorDTO>> buscarInactivos() {
        try {
            return ResponseEntity.ok(proveedorService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String datos) {
        try {
            return ResponseEntity.ok(proveedorService.buscarDatosProveedor(datos));
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/proveedorId")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Optional<?>> buscarId(@RequestParam String id) {
        try {
            return ResponseEntity.status(200).body(proveedorService.buscarId(id));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Optional.of(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.of(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> agregar(@RequestBody ProveedorDTO proveedorDTO) {
        try {
            return ResponseEntity.status(201).body(proveedorService.agregar(proveedorDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> actualizar(@RequestBody ProveedorDTO proveedorDTO, @PathVariable String id) {
        try {
            return ResponseEntity.status(200).body(proveedorService.actualizar(proveedorDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            return ResponseEntity.status(200).body(proveedorService.habilitar(id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            proveedorService.eliminar(id);
            return ResponseEntity.status(200).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
