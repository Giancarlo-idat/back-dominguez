package com.store.dominguez.service.gestion;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    String handleFileUpload(MultipartFile file) throws Exception;

}
