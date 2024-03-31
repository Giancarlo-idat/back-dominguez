package com.store.dominguez.controller;


import com.store.dominguez.service.gestion.TipoTransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tipo-transaccion")
public class TipoTransaccionController {


    @Autowired
    private TipoTransaccionService tipoTransaccionService;

    @GetMapping
    public ResponseEntity<List<?>> getTransacciones() {
        try {
            return ResponseEntity.ok(tipoTransaccionService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList(e.getMessage()));
        }
    }
}
