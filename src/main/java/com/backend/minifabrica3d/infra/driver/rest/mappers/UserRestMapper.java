package com.backend.minifabrica3d.infra.driver.rest.mappers;

import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driver.rest.dto.LoginRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.LoginResponseDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.RegisterRequestDto;
import com.backend.minifabrica3d.infra.driver.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper( componentModel = "spring" )
public interface UserRestMapper {

    User toUserModel( RegisterRequestDto registerRequestDto );
    User toUserModel( LoginRequestDto loginRequestDto );
    @Mappings({
            @Mapping( target = "addresses", ignore = true ),
            @Mapping( target = "rol", expression = "java( userModel.getRol() != null ? userModel.getRol().name() : null )" ),
            @Mapping( target = "isActive", source = "active" )
    })
    UserDto toUserDto( User userModel );

}
