package com.store.dominguez.controller;


import com.store.dominguez.dto.EmpleadoDTO;
import com.store.dominguez.service.gestion.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(empleadoService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarActivos() {
        try {
            return ResponseEntity.ok(empleadoService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarInactivos() {
        try {
            return ResponseEntity.ok(empleadoService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String empleado) {
        try {
            List<EmpleadoDTO> empleados = empleadoService.buscarDatosEmpleado(empleado);
            return ResponseEntity.ok(empleados);
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }


    @GetMapping("/empleadoId")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Optional<?>> buscarId(@RequestParam String empleadoId) {
        try {
            return ResponseEntity.ok(empleadoService.buscarId(empleadoId));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Optional.ofNullable(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Optional.ofNullable(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> guardarEmpleado(@RequestBody EmpleadoDTO empleado) {
        try {
            return ResponseEntity.status(201).body(empleadoService.agregar(empleado));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            return ResponseEntity.status(400).body("Error al guardar el empleado".concat(e.getMessage()));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable String id, @RequestBody EmpleadoDTO empleadoDTO) {
        try {
            return ResponseEntity.ok(empleadoService.actualizar(empleadoDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar/empleado")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> eliminarEmpleado(@RequestParam String id) {
        try {
            empleadoService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PutMapping("/habilitar/empleado")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> habilitarEmpleado(@RequestParam String id) {
        try {
            empleadoService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

}
