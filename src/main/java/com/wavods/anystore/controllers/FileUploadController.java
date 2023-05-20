package com.wavods.anystore.controllers;


import com.wavods.anystore.gateways.FileUploadGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class FileUploadController {

    private final FileUploadGateway uploadGateway;

    @PostMapping(value = "/file/upload")
    public void fileUplaod(final MultipartFile image) throws IOException {
        uploadGateway.fileUpload(image);
    }

    @DeleteMapping(value = "/file/delete/{fileName}")
    public void deleteFile(@PathVariable String fileName) {
        uploadGateway.deleteFile(fileName);
    }

//    @GetMapping(value = "/file/download/{bucketName}/{fileName}")
//    public StreamingResponseBody downloadFile(@PathVariable String bucketName, @PathVariable String fileName, HttpServletResponse httpResponse) {
//        FileUpload downloadFile = uploadGateway.downloadFile(bucketName, fileName);
//        httpResponse.setContentType("application/octet-stream");
//        httpResponse.setHeader("Content-Disposition",
//                String.format("inline; filename=\"%s\"", downloadFile.getFileName()));
//        return outputStream -> {
//            outputStream.write(downloadFile.getFile());
//            outputStream.flush();
//        };
//    }
//
//    @GetMapping(value = "/file/download/{bucketName}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<InputStreamResource> findPhoto(@PathVariable String bucketName, @PathVariable String fileName, HttpServletResponse httpResponse) {
//        FileUpload downloadFile = uploadGateway.downloadFile(bucketName, fileName);
//        httpResponse.setContentType("application/octet-stream");
//        httpResponse.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", downloadFile.getFileName()));
//        InputStream myInputStream = new ByteArrayInputStream(downloadFile.getFile());
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(myInputStream));
//    }
}