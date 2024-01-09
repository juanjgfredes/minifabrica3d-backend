package com.backend.minifabrica3d.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Address {

    private Integer id;
    private String street;
    private int numberStreet;
    private Department department;
    private Integer numberDepartment;
    private String details;
    private List<User> users;

}
