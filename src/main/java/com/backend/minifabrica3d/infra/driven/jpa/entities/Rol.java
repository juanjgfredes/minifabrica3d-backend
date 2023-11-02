package com.backend.minifabrica3d.infra.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "rol", schema = "users" )
public class Rol {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @Enumerated(EnumType.STRING)
    private Role name;

}
