package com.lms.filesystem.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file) throws Exception;

    Resource loadFile(String filename) throws Exception;

    void deleteFile(String filename) throws Exception;
}
