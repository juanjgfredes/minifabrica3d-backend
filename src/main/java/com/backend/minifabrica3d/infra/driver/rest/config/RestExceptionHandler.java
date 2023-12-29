package com.backend.minifabrica3d.infra.driver.rest.config;

import com.backend.minifabrica3d.application.exceptions.CustomException;
import com.backend.minifabrica3d.infra.driver.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( CustomException.class )
    public ResponseEntity<ErrorDto> customException( CustomException exception ){
        String message = exception.getMessage();
        HttpStatus httpStatus = HttpStatus.resolve( exception.getStatusCode() );

        ErrorDto errorDto = new ErrorDto( message );
        return ResponseEntity
                .status( httpStatus )
                .body( errorDto );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<HashMap<String,List<ErrorDto>>> argumentException(MethodArgumentNotValidException exception ){
        List<ErrorDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach( error -> {
            String message = error.getDefaultMessage();

            errors.add( new ErrorDto( message ));
        });

        HashMap<String,List<ErrorDto>> errorsJson = new HashMap<>();
        errorsJson.put( "errors", errors );

        return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( errorsJson );
    }

}
