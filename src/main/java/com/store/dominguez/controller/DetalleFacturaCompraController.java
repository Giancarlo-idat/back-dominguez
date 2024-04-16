package com.store.dominguez.controller;

import com.store.dominguez.dto.DetalleFacturaCompraDTO;
import com.store.dominguez.service.gestion.DetalleFacturaCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-factura-compra")
public class DetalleFacturaCompraController {

    private final DetalleFacturaCompraService detalleFacturaCompraService;

    public DetalleFacturaCompraController(DetalleFacturaCompraService detalleFacturaCompraService) {
        this.detalleFacturaCompraService = detalleFacturaCompraService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleFacturaCompraDTO>> buscarTodos() {
        List<DetalleFacturaCompraDTO> detallesFacturaCompra = detalleFacturaCompraService.buscarTodos();
        return ResponseEntity.ok(detallesFacturaCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturaCompraDTO> buscarPorId(@PathVariable("id") String id) {
        return detalleFacturaCompraService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}