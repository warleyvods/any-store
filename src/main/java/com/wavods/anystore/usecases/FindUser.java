package com.wavods.anystore.usecases;


import com.wavods.anystore.domains.User;
import com.wavods.anystore.gateways.UserGateway;
import com.wavods.anystore.gateways.mappers.UserGatewayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public record FindUser(UserGateway userGateway, UserGatewayMapper userGatewayMapper) {

    public User byId(final Long id) {
        return userGateway.findById(id);
    }

    public Optional<User> byLogin(final String login) {
        return userGateway.findByLogin(login);
    }

    public Page<User> all(Pageable pageable) {
        return userGateway.getAll(pageable);
    }

    public Page<User> findByWithFilters(final Boolean status, final Pageable pageable) {
        return userGateway.getAllWithFilters(status, pageable);
    }
}
