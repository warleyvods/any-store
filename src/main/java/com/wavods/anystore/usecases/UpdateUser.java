package com.wavods.anystore.usecases;


import com.wavods.anystore.domains.User;
import com.wavods.anystore.exceptions.PermissionDeniedException;
import com.wavods.anystore.gateways.UserGateway;
import org.springframework.stereotype.Component;

@Component
public record UpdateUser(UserGateway userGateway) {

    private static final String ADMIN = "admin";

    public User update(final User user) {
        if (user.getLogin().equalsIgnoreCase(ADMIN)) {
            throw new PermissionDeniedException("You cannot change any developer attributes");
        }
        return userGateway.update(user);
    }

    public void updatePassword(final User user) {
        if (user.getLogin().equalsIgnoreCase(ADMIN)) {
            throw new PermissionDeniedException("You cannot change a developer password");
        }
        update(user);
    }
}
