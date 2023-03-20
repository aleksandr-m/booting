package com.example.booting.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String password;
}
