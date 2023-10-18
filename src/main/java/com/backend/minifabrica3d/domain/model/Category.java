package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {

    private Long id;
    private String name;
    private List<Product> products;

}
