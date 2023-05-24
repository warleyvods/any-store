package com.wavods.anystore.controllers;


import com.wavods.anystore.gateways.AmazonS3Gateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class FileUploadController {

    private final AmazonS3Gateway uploadGateway;

    @PostMapping(value = "/file/upload")
    public void fileUplaod(final MultipartFile image) throws IOException {
        uploadGateway.fileUpload(image);
    }

    @DeleteMapping(value = "/file/delete/{fileName}")
    public void deleteFile(@PathVariable String fileName) {
        uploadGateway.deleteFile(fileName);
    }
}