package com.wavods.anystore.controllers;


import com.wavods.anystore.controllers.dtos.requests.LoginRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.SignupUserRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.MessageResponse;
import com.wavods.anystore.controllers.mappers.UserMapper;
import com.wavods.anystore.domains.User;
import com.wavods.anystore.gateways.UserGateway;
import com.wavods.anystore.repositories.UserRepository;
import com.wavods.anystore.security.jwt.JWTUtils;
import com.wavods.anystore.security.services.UserDetailsImpl;
import com.wavods.anystore.usecases.CreateUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserGateway userGateway;
    private final JWTUtils jwtUtils;
    private final UserMapper userMapper;
    private final CreateUser createUser;

    @PostMapping("/login")
    public ResponseEntity<Void> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.login(), loginRequestDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        lastAccess(authentication);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(null);
    }

    private void lastAccess(Authentication authentication) {
        final Optional<User> user = userGateway.findByLogin(((UserDetailsImpl) authentication.getPrincipal()).username());
        if (user.isPresent()) {
            user.get().lastAccess();
            userGateway.save(user.get());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupUserRequestDTO signUpRequestDTO) {
        if (Boolean.TRUE.equals(userGateway.existsByLogin(signUpRequestDTO.login()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(userGateway.existsByEmail(signUpRequestDTO.email()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        final User user = userMapper.toDomainUser(signUpRequestDTO);
        return ResponseEntity.ok(createUser.create(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
    }
}
