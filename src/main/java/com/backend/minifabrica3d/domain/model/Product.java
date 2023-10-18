package com.backend.minifabrica3d.domain.model;

import lombok.Data;

@Data
public class Product {

	private Long id;
	private String name;
	private Category category;
	private Subcategory subcategory;
	private double price;
	private int stock;
	private String image;

}
