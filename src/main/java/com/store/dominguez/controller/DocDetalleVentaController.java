package com.store.dominguez.controller;

import com.store.dominguez.dto.DocDetalleVentaDTO;
import com.store.dominguez.dto.DocVentaDTO;
import com.store.dominguez.service.gestion.DocDetalleVentaService;
import com.store.dominguez.service.gestion.DocVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/document/docDetalleVenta")
public class DocDetalleVentaController {

    private final DocDetalleVentaService docDetalleVentaService;
    private final DocVentaService docVentaService;

    @Autowired
    public DocDetalleVentaController(DocDetalleVentaService docDetalleVentaService, DocVentaService docVentaService) {
        this.docDetalleVentaService = docDetalleVentaService;
        this.docVentaService = docVentaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador') or hasRole('Cliente')")
    public ResponseEntity<List<?>> docDetalleVentaList() {
        try {
            return ResponseEntity.ok(docDetalleVentaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador') or hasRole('Cliente')")
    public ResponseEntity<?> docDetalleVentaById(@PathVariable UUID id) {
        try {
            Optional<DocDetalleVentaDTO> docDetalleVenta = docDetalleVentaService.findById(id);
            if (docDetalleVenta.isPresent()) {
                return ResponseEntity.ok(docDetalleVenta);
            } else {
                return ResponseEntity.status(404).body("No se encontró el detalle de venta con el id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
