package com.backend.minifabrica3d.domain.model;

import lombok.Data;

@Data
public class CartItem {

    private Integer id;
    private Cart cart;
    private Product product;
    private int amount;

}
