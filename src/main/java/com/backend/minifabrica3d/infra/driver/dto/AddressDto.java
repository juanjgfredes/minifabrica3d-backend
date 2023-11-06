package com.backend.minifabrica3d.infra.driver.dto;

public record AddressDto(

        Long id,
        String street,
        int numberStreet,
        String department,
        Integer numberDepartment,
        String details

) {
}
