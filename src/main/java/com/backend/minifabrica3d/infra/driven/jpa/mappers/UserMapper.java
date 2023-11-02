package com.backend.minifabrica3d.infra.driven.jpa.mappers;

import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = RolMapper.class )
public interface UserMapper {

    @Mapping( target = "rol" , source="rol.name" )
    User toUserModel( UserEntity userEntity );

    @Mapping( target = "rol", source="rol" )
    UserEntity toUserEntity( User userModel );

}
