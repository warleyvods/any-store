package com.wavods.anystore.gateways;

import com.wavods.anystore.domains.FileUpload;
import com.wavods.anystore.exceptions.FileNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageGateway {

    private final FileUploadGateway fileUploadGateway;

    public FileUpload save(final MultipartFile image) throws IOException {
        return fileUploadGateway.fileUpload(image);
    }

    public void imageValidation(MultipartFile imageFile) {
        final String extension = getExtension(imageFile).orElseThrow(FileNotSupportedException::new);

        if (!validateExtension(extension)) {
            throw new FileNotSupportedException("Extension file not supported: " + extension);
        }
    }

    private Optional<String> getExtension(MultipartFile imageFile) {
        return Optional.ofNullable(imageFile.getOriginalFilename())
                .filter(file -> file.contains("."))
                .map(file -> file.substring(imageFile.getOriginalFilename().lastIndexOf(".") + 1));
    }

    private boolean validateExtension(String extension) {
        return List.of("jpg", "jpeg", "png", "pdf").contains(extension);
    }
}
