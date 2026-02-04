package com.github.GaskaPiotr.spring_boot_boilerplate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String email;

    @NotBlank
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
