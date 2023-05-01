package com.wavods.anystore.gateways;


import com.wavods.anystore.domains.User;
import com.wavods.anystore.exceptions.UserNotFoundException;
import com.wavods.anystore.gateways.mappers.UserGatewayMapper;
import com.wavods.anystore.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public record UserGateway(UserRepository userRepository, UserGatewayMapper userGatewayMapper) {

    private static final String MSG = "user not found!";

    public User save(final User user) {
        return userGatewayMapper.toDomain(userRepository.save(userGatewayMapper.toEntity(user)));
    }

    public Page<User> getAll(final Pageable pageable) {
        return userRepository.findAll(pageable).map(userGatewayMapper::toDomain);
    }

    public User findById(final Long id) {
        return userGatewayMapper.toDomain(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(MSG)));
    }

    public Optional<User> findByLogin(final String login) {
        return Optional.ofNullable(userGatewayMapper.toDomain(userRepository.findByLoginIgnoreCase(login)));
    }

    public Optional<User> findByEmail(final String email) {
        return Optional.ofNullable(userGatewayMapper.toDomain(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(MSG))));
    }

    public User update(final User user) {
        return userGatewayMapper.toDomain(userRepository.save(userGatewayMapper.toEntity(user)));
    }

    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }

    public Boolean existsByLogin(final String login) {
        return userRepository.existsByLogin(login);
    }

    public Boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }
}
