package com.store.dominguez.controller;

import com.store.dominguez.dto.GuiaSalidaDTO;
import com.store.dominguez.service.gestion.GuiaSalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/myorders/guiaSalida")
public class GuiaSalidaController {

    @Autowired
    private GuiaSalidaService guiaSalidaService;

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<GuiaSalidaDTO>> getGuiasSalida() {
        try {
            return ResponseEntity.ok(guiaSalidaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}
