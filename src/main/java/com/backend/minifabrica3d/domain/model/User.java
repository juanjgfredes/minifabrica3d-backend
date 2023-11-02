package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private List<Address> addresses;
    private ERol rol;
    private LocalDateTime createdAt;
    private Cart cart;

}
