package com.github.GaskaPiotr.spring_boot_boilerplate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
