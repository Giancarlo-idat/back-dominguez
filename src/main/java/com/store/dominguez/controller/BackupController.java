package com.store.dominguez.controller;

import com.store.dominguez.service.impl.DatabaseBackupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/backup")
public class BackupController {

    private final DatabaseBackupServiceImpl backupService;

    @Autowired
    public BackupController(DatabaseBackupServiceImpl backupService) {
        this.backupService = backupService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBackup() {
        boolean success = backupService.createBackup();
        if (success) {
            return ResponseEntity.ok("Backup creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el backup.");
        }
    }

    @PostMapping
    public ResponseEntity<String> restoreBackup(@Value("${backup.path}") String backupPath) {
        String backupFilePath = backupPath + File.separator + "backup_file_name.sql";
        boolean result = backupService.restoreBackup(backupFilePath);
        if (result) {
            return ResponseEntity.ok("La restauración de la base de datos se realizó correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al restaurar la base de datos.");
        }
    }
}
