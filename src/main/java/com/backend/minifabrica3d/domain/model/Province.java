package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Province {

    private Integer id;
    private String name;
    private List<Department> departments;

}
