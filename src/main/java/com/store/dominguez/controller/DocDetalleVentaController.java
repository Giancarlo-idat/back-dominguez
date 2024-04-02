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
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> docDetalleVentaList() {
        try {
            return ResponseEntity.ok(docDetalleVentaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/venta/{idVenta}/detalle/{idDetalle}")
    public ResponseEntity<?> buscarIdDocDetalleVenta(@PathVariable UUID idVenta, @PathVariable UUID idDetalle, Authentication authentication) {
        try {

            // Obtener detalle de la autenticacion
            String userEmail = authentication.getName();

            // Buscar el documento del detalle de venta por su ID, cargando el cliente asociado
            Optional<DocVentaDTO> docVentaOptional = docVentaService.buscarIdDocVenta(idVenta);

            if (docVentaOptional.isPresent()) {
                // Verificar si el cliente asociado a la venta coincide con el usuario autenticado
                DocVentaDTO docVenta = docVentaOptional.get();
                String clienteEmail = docVenta.getCliente().getEmail();

                if (!userEmail.equals(clienteEmail)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para acceder a este recurso");
                }

                // Buscar el detalle de venta por su ID
                Optional<DocDetalleVentaDTO> detalleVentaOptional = docDetalleVentaService.findByDocVentaId(idDetalle);

                if (detalleVentaOptional.isPresent()) {
                    // Verificar si el detalle de venta pertenece a la venta especificada
                    DocDetalleVentaDTO detalleVenta = detalleVentaOptional.get();
                    UUID ventaIdDelDetalle = detalleVenta.getVenta().getIdVenta();

                    if (!idVenta.equals(ventaIdDelDetalle)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El detalle de venta no pertenece a la venta especificada");
                    }

                    return ResponseEntity.ok(detalleVenta);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }
}
