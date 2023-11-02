package com.backend.minifabrica3d.infra.driven.jpa.mappers;

import com.backend.minifabrica3d.domain.model.ERol;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Rol;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper( componentModel = "spring" )
public interface RolMapper {

    ERol toERolModel( Role role );

    @Named( "toRoleFromERol" )
    Role toRole( ERol eRolModel );

    @Mapping( target = "name", source = ".", qualifiedByName = "toRoleFromERol" )
    Rol toRol( ERol eRolModel );

}
