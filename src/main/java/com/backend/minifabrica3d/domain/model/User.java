package com.backend.minifabrica3d.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private List<Address> addresses;
    private ERol rol;
    private LocalDateTime createdAt;
    private Cart cart;

}
