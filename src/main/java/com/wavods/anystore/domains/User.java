package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String login;
    private String email;
    private String password;
    private Boolean admin;
    private Boolean active;
    private LocalDate createdAt;
    private LocalDateTime lastAccess;

    public void lastAccess() {
        this.lastAccess = now();
    }

    public Boolean isAdmin() {
        return this.admin;
    }
}
