package com.lms.filesystem.service;

import com.lms.filesystem.exception.FileSystemException;
import com.lms.filesystem.messages.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        String fileExtension;
        String extension = Files.probeContentType(rootLocation.resolve(file.getOriginalFilename()));
        switch (extension) {
            case "image/jpeg":
                fileExtension = "jpg";
                break;
            case "image/png":
                fileExtension = "png";
                break;
            case "application/pdf":
                fileExtension = "pdf";
                break;
            case "application/msword":
                fileExtension = "doc";
                break;
            case "application/vnd.ms-excel":
                fileExtension = "xls";
                break;
            default:
                throw new FileSystemException(Messages.get("NotSupportedFileType"));
        }
        String modifiedFileName = "" + System.currentTimeMillis() + "." + fileExtension;
        Files.copy(file.getInputStream(), this.rootLocation.resolve(modifiedFileName));
        return modifiedFileName;
    }


    @Override
    public Resource loadFile(String filename) throws Exception {
        Path file = rootLocation.resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void deleteFile(String filename) throws Exception {
        File file = new File(filename);
        Path path = rootLocation.resolve(file.getName());
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException(Messages.get("unableToInitializeStorage"));
        }
    }

}
