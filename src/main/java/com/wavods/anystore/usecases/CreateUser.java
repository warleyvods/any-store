package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.User;
import com.wavods.anystore.gateways.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record CreateUser(UserGateway userGateway) {

    public User create(final User user) {
        log.info("[createUser: {}] Creating new user", user.getLogin());
        return userGateway.save(user);
    }
}
