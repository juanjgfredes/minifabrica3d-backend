package com.backend.minifabrica3d.infra.driver.rest.dto;

public record AddressDto(

        Long id,
        String street,
        int numberStreet,
        String department,
        Integer numberDepartment,
        String details

) {
}
