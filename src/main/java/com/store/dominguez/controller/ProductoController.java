package com.store.dominguez.controller;


import com.store.dominguez.dto.ProductoDTO;
import com.store.dominguez.service.gestion.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<?>> buscarTodos() {
        try {
            return ResponseEntity.ok(productoService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarActivos() {
        try {
            return ResponseEntity.ok(productoService.buscarActivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarInactivos() {
        try {
            return ResponseEntity.ok(productoService.buscarInactivo());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/modelo")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarPorModelo(@RequestParam String modelo) {
        try {
            return ResponseEntity.ok(productoService.buscarModelo(modelo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/marca")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarPorMarca(@RequestParam String marca) {
        try {
            return ResponseEntity.ok(productoService.buscarPorMarca(marca));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/categoria")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarPorCategoria(@RequestParam String categoria) {
        try {
            return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/precio")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('ADMIN')")
    public ResponseEntity<List<?>> buscarPorRangoPrecio(@RequestParam BigDecimal precioMin, @RequestParam BigDecimal precioMax) {
        try {
            return ResponseEntity.ok(productoService.buscarPorRangoPrecio(precioMin, precioMax));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Collections.singletonList(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ALMACEN') or hasRole('ADMIN')")
    public ResponseEntity<?> buscarId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productoService.buscarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> agregar(@RequestPart ProductoDTO productoDTO, @RequestParam("imagen") MultipartFile imagen) {
        try {

            if (!imagen.isEmpty()) {
                String fileName = UUID.randomUUID().toString();
                byte[] bytes = imagen.getBytes();
                long fileSize = imagen.getSize();
                long maxFileSize = 5 * 1024 * 1024;
                String fileOriginalName = imagen.getOriginalFilename();
                if (fileSize > maxFileSize) {
                    throw new Exception("El archivo es demasiado grande");
                }
                String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                String newFileName = fileName + fileExtension;
                Path directorioImagenes = Paths.get("src/main/resources/images");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                File folder = new File("src/main/resources/images");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                Path path = Paths.get("src/main/resources/images/" + newFileName);
                Files.write(path, bytes);

            }
            return ResponseEntity.status(201).body(productoService.agregar(productoDTO));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody ProductoDTO productoDTO) {
        try {
            return ResponseEntity.ok(productoService.actualizar(productoDTO, id));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/habilitar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> habilitar(@PathVariable String id) {
        try {
            productoService.habilitar(id);
            return ResponseEntity.status(204).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
