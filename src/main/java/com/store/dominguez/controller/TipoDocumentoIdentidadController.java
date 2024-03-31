package com.store.dominguez.controller;


import com.store.dominguez.dto.TipoDocumentoIdentidadDTO;
import com.store.dominguez.service.gestion.TipoDocumentoIdentidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-documento-identidad")
public class TipoDocumentoIdentidadController {

    @Autowired
    private TipoDocumentoIdentidadService tipoDocumentoIdentidadService;

    @GetMapping
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarActivos() {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarInactivos() {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String tipoDocumentoIdentidad) {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarTipoDocumentoIdentidad(tipoDocumentoIdentidad));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Optional<?>> buscarId(@RequestParam String id) {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarId(id));
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body(Optional.of(Collections.singletonList(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.of(Collections.singletonList(e.getMessage())));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> agregar(@RequestBody TipoDocumentoIdentidadDTO tipoDocumentoIdentidad) {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.agregar(tipoDocumentoIdentidad));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO) {
        try {
            return ResponseEntity.ok(tipoDocumentoIdentidadService.actualizar(tipoDocumentoIdentidadDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            tipoDocumentoIdentidadService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            tipoDocumentoIdentidadService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
