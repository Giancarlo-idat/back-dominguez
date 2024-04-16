package com.store.dominguez.controller;

import com.store.dominguez.dto.FacturaCompraDTO;
import com.store.dominguez.service.gestion.FacturaCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura-compra")
public class FacturaCompraController {

    private final FacturaCompraService facturaCompraService;

    public FacturaCompraController(FacturaCompraService facturaCompraService) {
        this.facturaCompraService = facturaCompraService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaCompraDTO>> buscarTodos() {
        List<FacturaCompraDTO> facturasCompra = facturaCompraService.buscarTodos();
        return ResponseEntity.ok(facturasCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaCompraDTO> buscarPorId(@PathVariable("id") String id) {
        return facturaCompraService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}