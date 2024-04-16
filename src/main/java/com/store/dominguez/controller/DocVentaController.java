package com.store.dominguez.controller;


import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.service.gestion.DocVentaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/document/docventa")
public class DocVentaController {

    @Autowired
    private DocVentaService docVentaService;


    @GetMapping
    @PreAuthorize("hasRole('Administrador') or hasRole('Cliente)")
    public ResponseEntity<List<?>> obtenerDocumentos() {
        try {
            return ResponseEntity.ok(docVentaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador') or hasRole('Cliente)")
    public ResponseEntity<?> obtenerDocVenta(@PathVariable String id) {
        try {
            return ResponseEntity.ok(docVentaService.buscarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearDocVenta(@RequestBody DocVentaDTO docVentaDTO) {
        try {
            return ResponseEntity.ok(docVentaService.agregar(docVentaDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

}