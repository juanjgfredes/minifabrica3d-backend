package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Subcategory {

    private Integer id;
    private String name;
    private List<Product> products;

}
