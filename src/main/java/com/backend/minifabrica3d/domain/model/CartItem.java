package com.backend.minifabrica3d.domain.model;

import lombok.Data;

@Data
public class CartItem {

    private Long id;
    private Cart cart;
    private Product product;
    private int amount;

}
