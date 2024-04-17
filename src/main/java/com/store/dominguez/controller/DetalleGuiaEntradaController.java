package com.store.dominguez.controller;

import com.store.dominguez.dto.DetalleGuiaEntradaDTO;
import com.store.dominguez.service.gestion.DetalleGuiaEntradaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-guia-entrada")
public class DetalleGuiaEntradaController {

    private final DetalleGuiaEntradaService detalleGuiaEntradaService;

    public DetalleGuiaEntradaController(DetalleGuiaEntradaService detalleGuiaEntradaService) {
        this.detalleGuiaEntradaService = detalleGuiaEntradaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleGuiaEntradaDTO>> buscarTodos() {
        List<DetalleGuiaEntradaDTO> detallesGuiaEntrada = detalleGuiaEntradaService.buscarTodos();
        return ResponseEntity.ok(detallesGuiaEntrada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleGuiaEntradaDTO> buscarPorId(@PathVariable("id") String id) {
        return detalleGuiaEntradaService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}