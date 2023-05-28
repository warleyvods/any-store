package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;

@Getter
@Setter
@Entity(name = "`user`")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Basic(optional = false)
    @Column(nullable = false)
    private String login;

    @Size(max = 50)
    @NotBlank
    @Column(nullable = false)
    private String email;

    @Size(max = 100)
    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin = false;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;

    @Column(updatable = false)
    private LocalDate createdAt;

    private LocalDateTime lastAccess;

    @PrePersist
    public void prePersist() {
        this.createdAt = now();
    }
}
