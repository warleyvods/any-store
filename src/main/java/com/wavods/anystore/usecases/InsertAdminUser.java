package com.wavods.anystore.usecases;


import com.wavods.anystore.domains.User;
import com.wavods.anystore.gateways.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record InsertAdminUser(UserGateway userGateway) {

    private static final String ADMIN = "admin";

    public void insertAdminUser() {
        if (userGateway.findByLogin(ADMIN).isEmpty()) {
            log.debug("Administrator user not found, creating...");
            final var user = new User();
            user.setName("Administrator");
            user.setLogin(ADMIN);
            user.setAdmin(true);
            user.setActive(true);
            user.setPassword(new BCryptPasswordEncoder().encode("baguvix"));
            user.setEmail("email@email.com");
            userGateway.save(user);
        } else {
            log.info("insertAdminUser: admin user already exists");
        }
    }
}
