package com.store.dominguez.controller;


import com.store.dominguez.dto.RolDTO;
import com.store.dominguez.service.gestion.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {
    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<RolDTO>> buscarRoles() {
        try {
            return ResponseEntity.ok(rolService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/activos")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<RolDTO>> buscarRolesActivos() {
        try {
            return ResponseEntity.ok(rolService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/inactivos")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<RolDTO>> buscarRolesInactivos() {
        try {
            return ResponseEntity.ok(rolService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/buscar")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<?>> buscarPorDatos(@RequestParam String datos) {
        try {
            return ResponseEntity.ok(rolService.buscarRoles(datos));
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/rolId")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<?> buscarId(@RequestParam String id) {
        try {
            return ResponseEntity.status(200).body(rolService.buscarId(id));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PostMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<?> agregar(@RequestBody RolDTO rolDTO) {
        try {
            return ResponseEntity.status(201).body(rolService.agregar(rolDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody RolDTO rolDTO) {
        try {
            return ResponseEntity.status(200).body(rolService.actualizar(rolDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            rolService.eliminar(id);
            return ResponseEntity.status(200).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/rolId")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<?> habilitar(@RequestParam String id) {
        try {
            return ResponseEntity.status(200).body(rolService.habilitar(id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
