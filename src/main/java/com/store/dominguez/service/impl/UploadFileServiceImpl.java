package com.store.dominguez.service.impl;

import com.store.dominguez.service.gestion.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();
            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;
            if (fileSize > maxFileSize) {
                throw new Exception("El archivo es demasiado grande");
            }

            if (!fileOriginalName.endsWith(".jpg")
                    && !fileOriginalName.endsWith(".png")
                    && !fileOriginalName.endsWith(".jpeg")) {
                throw new Exception("El archivo no es una imagen");
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

            File folder = new File("src/main/resources/images");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path path = Paths.get("src/main/resources/images/" + newFileName);
            Files.write(path, bytes);
            return "Imagen subida correctamente";

        } catch (Exception e) {
            throw new Exception("Error al subir el archivo".concat(e.getMessage()));
        }
    }
}
