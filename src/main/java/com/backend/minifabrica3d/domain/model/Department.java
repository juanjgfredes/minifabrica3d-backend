package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    private Long id;
    private String name;
    private Province province;
    private List<Address> addresses;

}
