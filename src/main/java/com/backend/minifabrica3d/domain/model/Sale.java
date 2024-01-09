package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sale {

    private Integer id;
    private LocalDateTime date;
    private Product product;
    private User user;
    private double price;
    private int amount;

}
