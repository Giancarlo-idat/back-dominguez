package com.store.dominguez.controller;

import com.store.dominguez.dto.DetalleOrdenCompraDTO;
import com.store.dominguez.service.gestion.DetalleOrdenCompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-orden-compra")
public class DetalleOrdenCompraController {

    private final DetalleOrdenCompraService detalleOrdenCompraService;

    public DetalleOrdenCompraController(DetalleOrdenCompraService detalleOrdenCompraService) {
        this.detalleOrdenCompraService = detalleOrdenCompraService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleOrdenCompraDTO>> buscarTodos() {
        List<DetalleOrdenCompraDTO> detallesOrdenCompra = detalleOrdenCompraService.buscarTodos();
        return ResponseEntity.ok(detallesOrdenCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenCompraDTO> buscarPorId(@PathVariable("id") String id) {
        return detalleOrdenCompraService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleOrdenCompraDTO> agregar(@RequestBody DetalleOrdenCompraDTO detalleOrdenCompraDTO) {
        DetalleOrdenCompraDTO nuevoDetalleOrdenCompra = detalleOrdenCompraService.agregar(detalleOrdenCompraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalleOrdenCompra);
    }
}
