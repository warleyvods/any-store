package com.wavods.anystore.security.services;


import com.wavods.anystore.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.TRUE;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userGateway;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final var user = userGateway.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with login: " + login));

        final var authorityListAdmin = createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        final var authorityListUser = createAuthorityList("ROLE_USER");

        return UserDetailsImpl.build(user, TRUE.equals(user.isAdmin()) ? authorityListAdmin : authorityListUser);
    }
}
