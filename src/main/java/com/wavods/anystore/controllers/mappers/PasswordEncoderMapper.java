package com.wavods.anystore.controllers.mappers;

import com.wavods.anystore.annotations.EncodedMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public record PasswordEncoderMapper(PasswordEncoder passwordEncoder) {

    @EncodedMapping
    public String encode(final String password) {
        return passwordEncoder.encode(password);
    }
}
