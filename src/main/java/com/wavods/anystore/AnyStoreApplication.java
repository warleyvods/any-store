package com.wavods.anystore;

import com.wavods.anystore.usecases.InsertAdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
public class AnyStoreApplication {

    private final InsertAdminUser insert;

    public static void main(String[] args) {
        SpringApplication.run(AnyStoreApplication.class, args);
    }

    @Bean
    protected InitializingBean sendDataBase() {
        return insert::insertAdminUser;
    }
}
