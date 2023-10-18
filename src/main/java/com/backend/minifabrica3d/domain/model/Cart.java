package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private Long id;
    private User user;
    private List<CartItem> cartItems;

}
