package com.store.dominguez.controller;


import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.service.gestion.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @PreAuthorize("hasRole('Cliente') or hasRole('Almacen') or hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(productoService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarActivos() {
        try {
            return ResponseEntity.ok(productoService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarInactivos() {
        try {
            return ResponseEntity.ok(productoService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/modelo")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorModelo(@RequestParam String modelo) {
        try {
            return ResponseEntity.ok(productoService.buscarModelo(modelo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/marca")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorMarca(@RequestParam String marca) {
        try {
            return ResponseEntity.ok(productoService.buscarPorMarca(marca));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/categoria")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorCategoria(@RequestParam String categoria) {
        try {
            return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/precio")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorRangoPrecio(@RequestParam BigDecimal precioMin, @RequestParam BigDecimal precioMax) {
        try {
            return ResponseEntity.ok(productoService.buscarPorRangoPrecio(precioMin, precioMax));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ALMACEN') or hasRole('Administrador')")
    public ResponseEntity<?> buscarId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productoService.buscarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> agregar(@RequestBody ProductoDTO productoDTO) {
        try {
            return ResponseEntity.status(201).body(productoService.agregar(productoDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody ProductoDTO productoDTO) {
        try {
            return ResponseEntity.ok(productoService.actualizar(productoDTO, id));
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
            productoService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            productoService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
