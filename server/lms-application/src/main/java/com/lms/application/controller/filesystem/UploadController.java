package com.lms.application.controller.filesystem;

import com.lms.common.dto.filesystem.DeleteFileRequest;
import com.lms.common.dto.filesystem.LoadFileResponse;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.filesystem.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping(path = "/filesystem/storage-api")
public class UploadController {

    private final FileStorageService storageService;

    @Autowired
    public UploadController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/store-file")
    public ActionResponseWithData<String> storeFile(@RequestParam("file") MultipartFile file) throws Exception {
        return new ActionResponseWithData<>(storageService.storeFile(file),true);
    }

    @GetMapping(value = "/load-file/{filename}/{suffix}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponseWithData<LoadFileResponse> getListFiles(@PathVariable String filename, @PathVariable String suffix) throws Exception {
        LoadFileResponse loadFileResponse = new LoadFileResponse();
        loadFileResponse.setFileUrl(MvcUriComponentsBuilder.fromMethodName(DownloadController.class, "getFile", filename + "." + suffix).build().getPath());
        return new ActionResponseWithData<>(loadFileResponse, true);
    }

    @PostMapping("/delete-file")
    public ActionResponse deleteFile(@RequestBody DeleteFileRequest deleteFileRequest) throws Exception {
        storageService.deleteFile(deleteFileRequest.getFileUrl());
        return new ActionResponse(true);
    }

}