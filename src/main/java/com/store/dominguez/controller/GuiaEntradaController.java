package com.store.dominguez.controller;

import com.store.dominguez.dto.GuiaEntradaDTO;
import com.store.dominguez.service.gestion.GuiaEntradaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guia-entrada")
public class GuiaEntradaController {

    private final GuiaEntradaService guiaEntradaService;

    public GuiaEntradaController(GuiaEntradaService guiaEntradaService) {
        this.guiaEntradaService = guiaEntradaService;
    }

    @GetMapping
    public ResponseEntity<List<GuiaEntradaDTO>> buscarTodos() {
        List<GuiaEntradaDTO> guiasEntrada = guiaEntradaService.buscarTodos();
        return ResponseEntity.ok(guiasEntrada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuiaEntradaDTO> buscarPorId(@PathVariable("id") String id) {
        return guiaEntradaService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
