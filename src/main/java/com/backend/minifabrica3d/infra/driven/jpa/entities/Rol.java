package com.backend.minifabrica3d.infra.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "rol", schema = "users")
public class Rol {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "rol_id_seq" )
    @SequenceGenerator( name = "rol_id_seq", sequenceName = "rol_id_seq", schema = "users")
    private short id;

    @Enumerated( EnumType.STRING )
    private Role name;

}
