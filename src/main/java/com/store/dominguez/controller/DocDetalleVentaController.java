package com.store.dominguez.controller;

import com.store.dominguez.service.gestion.DocDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/myorders/document/docDetalleVenta")
public class DocDetalleVentaController {

    @Autowired
    private DocDetalleVentaService docDetalleVentaService;

    @GetMapping
    @PreAuthorize("hasRole('Cliente') or hasRole('Administrador')")
    public ResponseEntity<List<?>> docDetalleVentaList() {
        try {
            return ResponseEntity.ok(docDetalleVentaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }
}
