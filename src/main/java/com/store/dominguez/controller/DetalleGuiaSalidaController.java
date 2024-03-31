package com.store.dominguez.controller;

import com.store.dominguez.service.gestion.DetalleGuiaSalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/myorders/detalleGuiaSalida")
public class DetalleGuiaSalidaController {

    @Autowired
    private DetalleGuiaSalidaService detalleGuiaSalidaService;

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> getDetallesGuiaSalida() {
        try {
            return ResponseEntity.ok(detalleGuiaSalidaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

}
