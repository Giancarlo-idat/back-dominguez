package com.store.dominguez.controller;

import com.store.dominguez.dto.OrdenCompraDTO;
import com.store.dominguez.service.gestion.OrdenCompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden-compra")
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenCompraDTO>> buscarTodos() {
        List<OrdenCompraDTO> ordenesCompra = ordenCompraService.buscarTodos();
        return ResponseEntity.ok(ordenesCompra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraDTO> buscarPorId(@PathVariable("id") String id) {
        return ordenCompraService.buscarId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdenCompraDTO> agregar(@RequestBody OrdenCompraDTO ordenCompraDTO) {
        OrdenCompraDTO nuevaOrdenCompra = ordenCompraService.agregar(ordenCompraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrdenCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompraDTO> actualizar(@RequestBody OrdenCompraDTO ordenCompraDTO, @PathVariable("id") String id) {
        OrdenCompraDTO ordenCompraActualizada = ordenCompraService.actualizar(ordenCompraDTO, id);
        return ResponseEntity.ok(ordenCompraActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/habilitar")
    public ResponseEntity<OrdenCompraDTO> habilitarOrdenCompra(@PathVariable("id") String id) {
        OrdenCompraDTO ordenCompraHabilitada = ordenCompraService.habilitar(id);
        return ResponseEntity.ok(ordenCompraHabilitada);
    }
}
