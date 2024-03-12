package com.store.dominguez.controller;


import com.store.dominguez.service.gestion.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {


    @Autowired
    UploadFileService uploadFileService;

    @PostMapping("/picture")
    private ResponseEntity<String> uploadPicture(MultipartFile file) throws Exception {
        return new ResponseEntity<>(uploadFileService.handleFileUpload(file), null, 200);
    }

}
