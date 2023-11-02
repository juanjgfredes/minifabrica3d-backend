package com.backend.minifabrica3d.infra.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table( name = "user", schema = "users")
public class UserEntity implements UserDetails {

        @Id
        @GeneratedValue()
        private Long id;
        private String username;
        private String email;
        private String password;
        private Boolean isActive = Boolean.TRUE;
        private Rol rol;
        @Column( name = "created_at" )
        private LocalDateTime createdAt;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Set.of( this.rol.getName().getAuthority() );
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return this.isActive;
        }
}
