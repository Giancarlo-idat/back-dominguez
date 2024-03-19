package com.store.dominguez.controller;

import com.store.dominguez.dto.CategoriaDTO;
import com.store.dominguez.service.gestion.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(categoriaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarActivos() {
        try {
            return ResponseEntity.ok(categoriaService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarInactivos() {
        try {
            return ResponseEntity.ok(categoriaService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String categoria) {
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoria(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<?>> buscarId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarId(id));
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body(Optional.of(Collections.singletonList(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.of(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> agregar(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            return ResponseEntity.status(201).body(categoriaService.agregar(categoriaDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody CategoriaDTO categoriaDTO) {
        try {
            return ResponseEntity.ok(categoriaService.actualizar(categoriaDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            categoriaService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            categoriaService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
