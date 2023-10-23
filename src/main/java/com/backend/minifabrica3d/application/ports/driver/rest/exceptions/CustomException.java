package com.backend.minifabrica3d.application.ports.driver.rest.exceptions;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private int statusCode;
    public CustomException( String message, int statusCode ) {
        super(message);
        this.statusCode = statusCode;
    }

}
