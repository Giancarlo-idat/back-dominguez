package com.store.dominguez.controller;


import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.model.DocVentaEntity;
import com.store.dominguez.service.gestion.DocVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/myorders/document/docVenta")
public class DocVentaController {

    @Autowired
    private DocVentaService docVentaService;

    @GetMapping()
    @PreAuthorize("hasRole('Cliente') or hasRole('Administrador')")
    public ResponseEntity<List<?>> docVentaList() {
        try {
            return ResponseEntity.ok(docVentaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/list/activos")
    /*@PreAuthorize("hasRole('Administrador')")*/
    public ResponseEntity<List<?>> docVentaListActivos() {
        try {
            return ResponseEntity.ok(docVentaService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/list/inactivos")
    /*@PreAuthorize("hasRole('Administrador')")*/
    public ResponseEntity<List<?>> docVentaListInactivos() {
        try {
            return ResponseEntity.ok(docVentaService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }


    @PostMapping
    @PreAuthorize("hasRole('Cliente') or hasRole('Administrador')")
    public ResponseEntity<?> agregarDocVenta(@RequestBody DocVentaDTO docVentaDTO) {
        try {
            return ResponseEntity.ok(docVentaService.agregar(docVentaDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    /*@PreAuthorize("hasRole('Administrador')")*/
    public ResponseEntity<?> actualizarDocVenta(@PathVariable String id, @RequestBody DocVentaDTO docVentaDTO) {
        try {
            return ResponseEntity.ok(docVentaService.actualizar(docVentaDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    /*@PreAuthorize("hasRole('Administrador')")*/
    public ResponseEntity<?> eliminarDocVenta(@PathVariable String id) {
        try {
            docVentaService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PutMapping("/habilitar/{id}")
    /*@PreAuthorize("hasRole('Administrador')")*/
    public ResponseEntity<?> habilitarDocVenta(@PathVariable String id) {
        try {
            docVentaService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

}
