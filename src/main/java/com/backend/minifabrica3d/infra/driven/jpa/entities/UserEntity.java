package com.backend.minifabrica3d.infra.driven.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "users", schema = "users")
public class UserEntity implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
        @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", schema = "users")
        private Integer id;
        private String username;
        private String email;
        private String password;
        @Builder.Default
        private Boolean isActive = Boolean.TRUE;
        @ManyToOne
        @JoinColumn( name = "id_rol" )
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
