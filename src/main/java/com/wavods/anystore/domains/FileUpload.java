package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileUpload {

    private String fileName;
    private String contentType;
    private Long size;
    private String url;
    private LocalDateTime createdAt;

    public FileUpload(String fileName, String contentType, Long size, String url, LocalDateTime createdAt) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.url = url;
        this.createdAt = createdAt;
    }
}
