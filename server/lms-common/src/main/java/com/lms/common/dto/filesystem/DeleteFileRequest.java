package com.lms.common.dto.filesystem;

public class DeleteFileRequest {

    private String fileUrl;

    public DeleteFileRequest() {
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
